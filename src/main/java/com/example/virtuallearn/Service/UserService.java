package com.example.virtuallearn.Service;

import com.example.virtuallearn.Constants.ResultInfoConstants;
import com.example.virtuallearn.Entity.*;
import com.example.virtuallearn.Exception.AlreadyExistException;
import com.example.virtuallearn.Exception.CategoryNotFoundException;
import com.example.virtuallearn.Exception.EnterValidCredentialException;
import com.example.virtuallearn.Repository.*;
import com.example.virtuallearn.Repository.Table.*;
import com.example.virtuallearn.UserAuthorisation.JWTUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final OtpRepository otpRepository;

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    private final PrivacyPolicyRepository privacyPolicyRepository;

    private final TermsOfServicesRepository termsOfServicesRepository;

    private final ChapterContentRepository chapterContentRepository;

    private final TestRepository testRepository;

    private final ResultRepository resultRepository;

    private final MyCourseRepository myCourseRepository;

    private final BlacklistRepository blacklistRepository;

    private final JWTUtility jwtUtility;

    private final HelpRepository helpRepository;

    private final NotificationRepository notificationRepository;


    //Request and Resend OTP
    public Long getotp(Otp otp) {
        long phoneNumber = otp.getPhoneNumber();

        UserTable userTable = userRepository.getUserByPhoneNumber(phoneNumber);
        if (userTable != null) {
            log.warn("phone number already registered ");
            throw new AlreadyExistException(ResultInfoConstants.PHONE_NUMBER_ALREADY_PRESENT);
        }

        OtpTable otpTable = otpRepository.getUserByPhoneNumber(phoneNumber);

        if (otpTable != null) {
            long id = otpTable.getId();
            otpRepository.deleteById(id);
        }

        String stringPhoneNumber=String.valueOf(phoneNumber);
        if(stringPhoneNumber.length() != 10){
            log.warn("enter valid mobile number ");
            throw new EnterValidCredentialException(ResultInfoConstants.ENTER_VALID_PHONE_NUMBER);
        }
        otpRepository.save(otp.toOtpTable());
        OtpTable savedOtpTable = otpRepository.getUserByPhoneNumber(phoneNumber);

        long resRandom = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);

        savedOtpTable.setOtp(resRandom);
        otpRepository.save(savedOtpTable);
        return resRandom;
    }

    //Signup
    public Long signUp(User user) {
        long enteredOtp = user.getOtp();
        long phoneNumber = user.getPhoneNumber();
        UserTable userTable = userRepository.getUserByPhoneNumber(phoneNumber);
        if (userTable != null) {
            log.warn("phone number already registered ");
            throw new AlreadyExistException(ResultInfoConstants.PHONE_NUMBER_ALREADY_PRESENT);
        }

        OtpTable otpTable = otpRepository.getUserByPhoneNumber(user.getPhoneNumber());
        if (otpTable == null) {
            log.warn("first request the otp");
            throw new AlreadyExistException(ResultInfoConstants.REQUEST_OTP);
        }

        long savedOtp = otpTable.getOtp();
        if(enteredOtp != savedOtp){
            log.warn("entered otp is wrong please enter the correct otp ");
            throw new AlreadyExistException(ResultInfoConstants.WRONG_OTP);
        }

        long id = otpTable.getId();
        otpRepository.deleteById(id);
        return userRepository.save(user.toUserTable()).getId();
    }

    //Profile Update
    public void profile(User user, Long phonenumber){
        UserTable userTable = userRepository.getUserByPhoneNumber(phonenumber);
        if(userTable==null) {
            log.warn("there is no account in this number");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_ACCOUNT_FOUND);
        }
        String gender = user.getGender();
        if (!gender.equals("Male") && !gender.equals("Female") && !gender.equals("Other") && !gender.equals("male") && !gender.equals("female") && !gender.equals("other") ){
            throw new EnterValidCredentialException(ResultInfoConstants.ENTER_VALID_GENDER);
        }

        UserTable saveduserTable = userRepository.getByUsername(user.getUsername());
        if(saveduserTable != null){
            log.warn("username already present use unique username");
            throw new EnterValidCredentialException(ResultInfoConstants.USERNAME_DUPLICATE);
        }

        Optional<UserTable> oldUserOptional = userRepository.findById(userTable.getId());

        String password = user.getPassword();
        if(isValid(password)) {
            UserTable newUser = user.toUserTable();
            newUser.setId(oldUserOptional.get().getId());
            newUser.setPhoneNumber(oldUserOptional.get().getPhoneNumber());
            newUser.setRole("USER");
            userRepository.save(newUser);
        }
        else{
            log.warn("Invalid password");
            throw new EnterValidCredentialException(ResultInfoConstants.ENTER_VALID_PASSWORD);
        }
    }

    public void privacy(User user, String username) {
        UserTable userTable = userRepository.getByUsername(username);
        if (userTable == null) {
            log.warn("There is no account in this username");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_ACCOUNT_FOUND);
        }

        String oldPassword = user.getOldPassword();
        String savedPassword = userTable.getPassword();

        if (oldPassword.equals(savedPassword)){
            String password = user.getPassword();
            if(isValid(password)) {
                userTable.setPassword(password);
            }
            else{
                log.warn("Invalid password");
                throw new EnterValidCredentialException(ResultInfoConstants.ENTER_VALID_PASSWORD);
            }
        }
        else{
            log.warn("Current password is wrong");
            throw new EnterValidCredentialException(ResultInfoConstants.INCORRECT_PASSWORD);
        }
        userRepository.save(userTable);
        Notification notification = new Notification(0,username,"Successfully changes your Password");
        notificationRepository.save(notification.toNotificationTable());
    }

    public Long forgot(Otp otp){
        long phoneNumber = otp.getPhoneNumber();
        UserTable userTable = userRepository.getUserByPhoneNumber(phoneNumber);

        if (userTable == null) {
            log.warn("phone number not registered ");
            throw new AlreadyExistException(ResultInfoConstants.PHONE_NUMBER_NOT_EXIST);
        }

        OtpTable otpTable = otpRepository.getUserByPhoneNumber(phoneNumber);

        if (otpTable != null) {
            long id = otpTable.getId();
            otpRepository.deleteById(id);
        }

        otpRepository.save(otp.toOtpTable());
        OtpTable savedOtpTable = otpRepository.getUserByPhoneNumber(phoneNumber);

        long resRandom = ThreadLocalRandom.current().nextInt(1000, 9999 + 1);

        savedOtpTable.setOtp(resRandom);
        otpRepository.save(savedOtpTable);
        return resRandom;
    }

    public Long reset(User user) {
        long enteredOtp = user.getOtp();
        long phoneNumber = user.getPhoneNumber();
        OtpTable otpTable = otpRepository.getUserByPhoneNumber(phoneNumber);
        if (otpTable == null) {
            log.warn("first request the otp");
            throw new AlreadyExistException(ResultInfoConstants.REQUEST_OTP);
        }

        long savedOtp = otpTable.getOtp();
        if(enteredOtp != savedOtp){
            log.warn("entered otp is wrong please enter the correct otp ");
            throw new AlreadyExistException(ResultInfoConstants.WRONG_OTP);
        }
        String password = user.getPassword();
        UserTable userTable = userRepository.getUserByPhoneNumber(phoneNumber);
        if(isValid(password)) {
            userTable.setPassword(user.getPassword());
        }
        else{
            log.warn("Invalid password");
            throw new EnterValidCredentialException(ResultInfoConstants.ENTER_VALID_PASSWORD);
        }

        long id = otpTable.getId();
        otpRepository.deleteById(id);

        userRepository.save(userTable);
        return userTable.getId();
    }

    //Password condition method
    public boolean isValid(String password) {

        //customizing validation messages
        Properties props = new Properties();
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("passay.properties");
        MessageResolver resolver = new PropertiesMessageResolver(props);
        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

                // length between 6 and 16 characters
                new LengthRule(6, 16),

                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // no whitespace
                new WhitespaceRule()
        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        return false;
    }

    public void editProfile(User user, String username) {
        UserTable userTable = userRepository.getByUsername(username);
        if (userTable == null) {
            log.warn("There is no account in this username");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_ACCOUNT_FOUND);
        }
        Optional<UserTable> oldUserOptional = userRepository.findById(userTable.getId());
        UserTable newUser = user.toUserTable();
        newUser.setId(oldUserOptional.get().getId());
        newUser.setPhoneNumber(oldUserOptional.get().getPhoneNumber());
        newUser.setPassword(oldUserOptional.get().getPassword());
        newUser.setUsername(oldUserOptional.get().getUsername());
        newUser.setRole(oldUserOptional.get().getRole());
        Notification notification = new Notification(0,username,"Your Profile Updated successfully");
        notificationRepository.save(notification.toNotificationTable());
        userRepository.save(newUser);

    }

    public List<Category> getAllCategories(Pageable page) {
        return categoryRepository.findAll(page)
                .stream()
                .map(CategoryTable::toCategory)
                .collect(Collectors.toList());
    }

    public List<Courses> getAllCourses(Pageable page) {
        return courseRepository.findAll(page)
                .stream()
                .map(CourseTable::toCourses)
                .collect(Collectors.toList());
    }

    public List<Courses> getAllCoursesByCategory(long categoryId) {
        if (categoryRepository.findByCategoryId(categoryId).isEmpty()) {
            log.warn("There is no such category in this id");
            throw new CategoryNotFoundException(ResultInfoConstants.NO_SUCH_CATEGORY);
        }
        List<Courses> list = courseRepository.getCourseByCategory(categoryId)
                .stream()
                .map(CourseTable::toCourses)
                .collect(Collectors.toList());
        return list;
    }

    public List<Overview> getOverviewByCourse(long courseId) {
        if (courseRepository.getOverviewByCourse(courseId).isEmpty()) {
            log.warn("There is no such course");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_SUCH_COURSE);
        }
        List<Overview> lists = courseRepository.getOverviewByCourse(courseId)
                .stream()
                .map(CourseTable::toOverview)
                .collect(Collectors.toList());
        return lists;
    }

    public List<PrivacyPolicy> getPrivacyPolicy() {
        return privacyPolicyRepository.findAll()
                .stream()
                .map(PrivacyPolicyTable::toPrivacyPolicy)
                .collect(Collectors.toList());
    }

    public List<TermsOfServices> getTermsOfServices() {
        return termsOfServicesRepository.findAll()
                .stream()
                .map(TermsOfServicesTable::toTermsOfServices)
                .collect(Collectors.toList());
    }
    public List<ChapterContent> getByCourse(long courseId) {
        List<ChapterContent> chapterContent = chapterContentRepository.findByCourseId(courseId)
                .stream()
                .map(ChapterContentTable::toChapterContent)
                .collect(Collectors.toList());

        if(chapterContent.isEmpty()){
            log.warn("There is no course in this id");
            throw new EnterValidCredentialException(ResultInfoConstants.EMPTY_CHAPTER);
        }
        return chapterContent;
    }

    public Profile profile(String username) {
        UserTable userTable = userRepository.getByUsername(username);
        if (userTable == null) {
            log.warn("There is no account in this username");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_ACCOUNT_FOUND);
        }
        return userTable.toProfile();
    }

    public List<Tests> getTest(long courseId,long chapterId) {

        List<Tests> list = testRepository.getTest(courseId,chapterId)
                .stream()
                .map(TestTable::toTests)
                .collect(Collectors.toList());

        if(list.isEmpty()){
            log.warn("invalid input");
            throw new EnterValidCredentialException(ResultInfoConstants.INVALID_INPUT);
        }
        return list;
    }


   public long takeTest(String username,Result result, long id) {
        long courseId= result.getCourseId();
        long chapterId= result.getChapterId();
        List<MyCourseTable> MyCourseTable = myCourseRepository.getMyCourses(username,courseId);
        if(MyCourseTable.isEmpty())
        {
            log.warn("first join the course to write test");
            throw new EnterValidCredentialException(ResultInfoConstants.JOIN_COURSE);
        }

        List<TestTable> test = testRepository.getTests(courseId,chapterId,id);
        if(test.isEmpty())
        {
            log.warn("No question present in this chapter for this id");
            throw new EnterValidCredentialException(ResultInfoConstants.WRONG_ID);
        }

        String question = test.get(0).getQuestion();
        List<ResultTable> resultTable = resultRepository.getTest(username,courseId,chapterId,question);
        if(!resultTable.isEmpty()){
            resultRepository.deleteById(resultTable.get(0).getId());
        }
        result.setUsername(username);
        result.setCourseId(courseId);
        result.setChapterId(chapterId);
        result.setQuestion(question);
        String answer = test.get(0).getAnswer();
        long markOption = result.getMarkOption();
        result.setAnswer(answer);

        if (markOption == 1)
            result.setMarkedAnswer(test.get(0).getA());
        else if (markOption == 2)
            result.setMarkedAnswer(test.get(0).getB());
        else if (markOption == 3)
            result.setMarkedAnswer(test.get(0).getC());
        else if (markOption == 4)
            result.setMarkedAnswer(test.get(0).getD());

        else {
            log.warn("invalid input");
            throw new EnterValidCredentialException(ResultInfoConstants.INVALID_INPUT);
        }

        if (answer.equals(result.getMarkedAnswer())) {
            result.setResult(true);
        } else {
            result.setResult(false);
        }
        resultRepository.save(result.toResultTable());

        return id;
    }

    public List<Results> getResult(String username, long courseId, long chapterId) {

        List<Results> list = resultRepository.getTests(username,courseId,chapterId)
                .stream()
                .map(ResultTable::toResults)
                .collect(Collectors.toList());

        if(list.isEmpty()){
            log.warn("invalid input");
            throw new EnterValidCredentialException(ResultInfoConstants.INVALID_INPUT);
        }
        return list;
    }

    public void joinCourse(String username,MyCourse myCourse) {
        long id = myCourse.getCourseId();
        //String course = myCourse.getCourse();

        List<MyCourseTable> MyCourseTable = myCourseRepository.getMyCourses(username,id);
        if(!MyCourseTable.isEmpty())
        {
            log.warn("you already joined the course");
            throw new EnterValidCredentialException(ResultInfoConstants.ALREADY_JOINED);
        }

        List<CourseTable> courseTable = courseRepository.getAllCourses(id);
        if(courseTable.isEmpty())
        {
            log.warn("There is no such course");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_SUCH_COURSE);
        }
        String course = courseTable.get(0).getCourse();
        myCourse.setUsername(username);
        myCourse.setCourse(course);
        myCourse.setIsongoing(true);
        myCourseRepository.save(myCourse.toMyCourseTable());

        Notification notification = new Notification(0,username,"Joined a new course - "+course);
        notificationRepository.save(notification.toNotificationTable());

    }

    public List<MyCourses> mycourse(String username) {
        UserTable userTable = userRepository.getByUsername(username);
        if (userTable == null) {
            log.warn("There is no account in this username");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_ACCOUNT_FOUND);
        }

        List<MyCourses> myCourses = myCourseRepository.getAllCourses(username)
                .stream()
                .map(MyCourseTable::toMyCourses)
                .collect(Collectors.toList());

        if(myCourses.isEmpty())
        {
            log.warn("mycourse is empty");
            throw new EnterValidCredentialException(ResultInfoConstants.EMPTY_MYCOURSE);
        }

        return myCourses;
    }

    public void logout(Blacklist blacklist) {
        String token = blacklist.getToken();


        BlacklistTable blacklistTable = blacklistRepository.getByUsername(token);
        if (blacklistTable != null) {
            log.warn("token already in blacklist");
            throw new EnterValidCredentialException(ResultInfoConstants.ALREADY_BLACKLIST);
        }

        String username = jwtUtility.getUsernameFromToken(token);
        blacklist.setUsername(username);
        blacklistRepository.save(blacklist.toBlacklistTable());
    }

    public List<Ongoing> ongoing(String username) {
        UserTable userTable = userRepository.getByUsername(username);
        if (userTable == null) {
            log.warn("There is no account in this username");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_ACCOUNT_FOUND);
        }

        List<Ongoing> myCourses = myCourseRepository.getOngoing(username,true)
                .stream()
                .map(MyCourseTable::toOngoing)
                .collect(Collectors.toList());

        if(myCourses.isEmpty())
        {
            log.warn("No ongoing courses");
            throw new EnterValidCredentialException(ResultInfoConstants.EMPTY_ONGOING);
        }

        return myCourses;
    }

    public List<Ongoing> completed(String username) {
        UserTable userTable = userRepository.getByUsername(username);
        if (userTable == null) {
            log.warn("There is no account in this username");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_ACCOUNT_FOUND);
        }

        List<Ongoing> myCourses = myCourseRepository.getOngoing(username,false)
                .stream()
                .map(MyCourseTable::toOngoing)
                .collect(Collectors.toList());

        if(myCourses.isEmpty())
        {
            log.warn("No completed courses");
            throw new EnterValidCredentialException(ResultInfoConstants.EMPTY_ONGOING);
        }

        return myCourses;
    }

    public void postHelpQuestion(Help help,String username){
        Long courseId = help.getCourseId();
        List<CourseTable> courseTable = courseRepository.getAllCourses(courseId);
        if (courseTable.isEmpty()){
            log.warn("No such course found");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_SUCH_COURSE);
        }
        help.setUsername(username);
        help.setAnswer("No Reply");
        helpRepository.save(help.toHelpTable());
    }

    public List<Helps> getHelpReply(String username) {
        List<Helps> helpTable = helpRepository.getByUsername(username)
                .stream()
                .map(HelpTable::toHelps)
                .collect(Collectors.toList());

        if(helpTable.isEmpty()){
            log.warn("Your chat is empty");
            throw new EnterValidCredentialException(ResultInfoConstants.EMPTY_CHAT);
        }

        return helpTable;
    }

    public Object getMark(String username,long courseId,long chapterId) {
        List<ResultTable> list = resultRepository.getTests(username,courseId,chapterId);

        if(list.isEmpty()){
            log.warn("invalid input");
            throw new EnterValidCredentialException(ResultInfoConstants.INVALID_INPUT);
        }

        List<String> result =new ArrayList<String>();

        List<ResultTable> resultTable = resultRepository.getResult(username,courseId,chapterId,true);
        long right = resultTable.size();

        List<ResultTable> resultTables = resultRepository.getResult(username,courseId,chapterId,false);
        long incorrect = resultTables.size();

        long total = right+incorrect;

        long correct = percentage(right,total);
        long wrong = percentage(incorrect,total);

        result.add("Passing Marks : 75/100");
        result.add("Correct : "+right+"/"+total);
        result.add("Correct Percentage: "+correct+"/"+100);
        result.add("Wrong : "+incorrect+"/"+total);
        result.add("Wrong Percentage: "+wrong+"/"+100);
        return result;
    }
    public long percentage(long mark,long total){
        long percentage = mark*100/total;
        return percentage;
    }

    public List<Notifications> getNotification(String username) {
        List<NotificationTable> notificationTables = notificationRepository.getNotificationByUsername(username,"all");

        if(notificationTables.isEmpty()){
            log.info("Notification is empty");
            throw new EnterValidCredentialException(ResultInfoConstants.EMPTY_NOTIFICATION);
        }
        return  notificationTables
                .stream()
                .map(NotificationTable::toNotifications)
                .collect(Collectors.toList());
    }
}

