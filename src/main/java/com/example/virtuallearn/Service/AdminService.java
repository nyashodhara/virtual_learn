package com.example.virtuallearn.Service;

import com.example.virtuallearn.Constants.ResultInfoConstants;
import com.example.virtuallearn.Entity.*;
import com.example.virtuallearn.Exception.AlreadyExistException;
import com.example.virtuallearn.Exception.EnterValidCredentialException;
import com.example.virtuallearn.Exception.NotFoundException;
import com.example.virtuallearn.Repository.*;
import com.example.virtuallearn.Repository.Table.*;
import com.example.virtuallearn.UserAuthorisation.JWTUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {

    private final CategoryRepository categoryRepository;

    private final CourseRepository courseRepository;

    private final ChapterRepository chapterRepository;

    private final PrivacyPolicyRepository privacyPolicyRepository;

    private final TermsOfServicesRepository termsOfServicesRepository;

    private final ChapterContentRepository chapterContentRepository;

    private final TestRepository testRepository;

    private final UserRepository userRepository;

    private final JWTUtility jwtUtility;

    private final BlacklistRepository blacklistRepository;

    private final HelpRepository helpRepository;

    private final InstructorRepository instructorRepository;

    private final NotificationRepository notificationRepository;

    public Long signUp(User user) {
        long phoneNumber = user.getPhoneNumber();
        UserTable userTable = userRepository.getUserByPhoneNumber(phoneNumber);
        if (userTable != null) {
            log.warn("phone number already registered ");
            throw new AlreadyExistException(ResultInfoConstants.PHONE_NUMBER_ALREADY_PRESENT);
        }

        UserTable saveduserTable = userRepository.getByUsername(user.getUsername());
        if(saveduserTable != null){
            log.warn("username already present use unique username");
            throw new EnterValidCredentialException(ResultInfoConstants.USERNAME_DUPLICATE);
        }
        user.setRole("ADMIN");

        return userRepository.save(user.toUserTable()).getId();
    }


    public void insert(Category category,String username) {

        isAdmin(username);

        String savedCategory = category.getCategory();
        CategoryTable categoryTable = categoryRepository.getByCategory(savedCategory);
        if (categoryTable != null) {
            log.warn("category already present ");
            throw new AlreadyExistException(ResultInfoConstants.CATEGORY_ALREADY_PRESENT);
        }

        categoryRepository.save(category.toCategoryTable());
    }


    public void insertCourse(Course course,String username) {

        isAdmin(username);

        long savedCategory = course.getCategoryId();
        List<CategoryTable> category = categoryRepository.findByCategoryId(savedCategory);

        if (category.isEmpty()) {
            log.warn("there is no such category ");
            throw new NotFoundException(ResultInfoConstants.NO_SUCH_CATEGORY);
        }

        long savedInstructor = course.getInstructorId();
        List<InstructorTable> instructor = instructorRepository.findByInstructorId(savedInstructor);

        if (instructor.isEmpty()) {
            log.warn("There is no such Instructor ");
            throw new NotFoundException(ResultInfoConstants.NO_SUCH_INSTRUCTOR);
        }

        String savedCourse = course.getCourse();
        CourseTable courseTable = courseRepository.getByCourse(savedCourse);
        if (courseTable != null) {
            log.warn("course already present ");
            throw new AlreadyExistException(ResultInfoConstants.COURSE_ALREADY_PRESENT);
        }
        course.setCategory(category.get(0).getCategory());
        course.setInstructor(instructor.get(0).getInstructor());
        courseRepository.save(course.toCourseTable());
        Notification notification = new Notification(0,"all","New Course Added - "+course.getCourse());
        notificationRepository.save(notification.toNotificationTable());
    }

    public void insertChapter(Chapter chapter,String username) {
        isAdmin(username);

        long savedCourse = chapter.getCourseId();
        List<CourseTable> course = courseRepository.findByCourseId(savedCourse);
        if (course.isEmpty()) {
            log.warn("there is no such course  ");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_SUCH_COURSE);
        }
        String savedChapter = chapter.getChapter();
        ChapterTable chapterTable = chapterRepository.getByChapter(savedCourse,savedChapter);
        if (chapterTable != null) {
            log.warn("chapter already exists");
            throw new EnterValidCredentialException(ResultInfoConstants.CHAPTER_ALREADY_EXISTS);
        }
        chapter.setCourse(course.get(0).getCourse());

        chapterRepository.save(chapter.toChapterTable());
    }

    public PrivacyPolicyTable insertPrivacyPolicy(PrivacyPolicy privacyPolicy,String username){
        isAdmin(username);

        String savedPrivacyPolicy = privacyPolicy.getPrivacyPolicy();
        PrivacyPolicyTable privacyPolicyTable = privacyPolicyRepository.getByPrivacyPolicy(savedPrivacyPolicy);
        if (privacyPolicyTable != null){
            log.warn("privacy policy already exists  ");
            throw new EnterValidCredentialException(ResultInfoConstants.PRIVACY_POLICY_ALREADY_EXISTS);
        }
        privacyPolicyRepository.deleteAll();
        return privacyPolicyRepository.save(privacyPolicy.toPrivacyPolicyTable());
    }

    public TermsOfServicesTable insertTermsOfService(TermsOfServices termsOfServices,String username){
        isAdmin(username);

        String savedTermsOfService = termsOfServices.getTermsOfServices();
        TermsOfServicesTable termsOfServicesTable = termsOfServicesRepository.getByTerms(savedTermsOfService);
        if (termsOfServicesTable != null){
            log.warn("terms of services already exists");
            throw new EnterValidCredentialException(ResultInfoConstants.TERMS_OF_SERVICES_ALREADY_EXISTS);
        }
        termsOfServicesRepository.deleteAll();
        return termsOfServicesRepository.save(termsOfServices.toTermsOfServicesTable());
    }

    public void insertContent(ChapterContent chapterContent,String username) {
        isAdmin(username);

        long savedChapter = chapterContent.getChapterId();

        ChapterTable chapterTable =chapterRepository.getChapter(savedChapter);
        if (chapterTable == null) {
            log.warn("there is no chapter in this id");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_SUCH_COURSE_AND_CHAPTER_EXISTS);
        }
        ChapterContentTable chapterContentTable = chapterContentRepository.getByCourseAndChapter(chapterContent.getContent(),chapterContent.getLink());
        if (chapterContentTable != null){
            log.warn("These contents are already present");
            throw new EnterValidCredentialException(ResultInfoConstants.CONTENT_ALREADY_EXISTS);
        }

        chapterContent.setChapter(chapterTable.getChapter());
        chapterContent.setCourse(chapterTable.getCourse());
        chapterContent.setCourseId(chapterTable.getCourseId());
        chapterContentRepository.save(chapterContent.toChapterContentTable());
    }

    public void insertTest(Test test,String username) {
        isAdmin(username);

        long savedChapter = test.getChapterId();

        ChapterTable chapterTable =chapterRepository.getChapter(savedChapter);

        if (chapterTable == null) {
            log.warn("there is no chapter in this id");
            throw new EnterValidCredentialException(ResultInfoConstants.NO_SUCH_COURSE_AND_CHAPTER_EXISTS);
        }
        long courseId = chapterTable.getCourseId();
        test.setCourseId(courseId);

        String savedQuestion = test.getQuestion();
        TestTable testTable = testRepository.getByQuestion(savedQuestion,savedChapter);
        if (testTable != null) {
            log.warn("question already exists in this chapter");
            throw new EnterValidCredentialException(ResultInfoConstants.QUESTION_ALREADY_EXISTS);
        }
        testRepository.save(test.toTestTable());
    }

    private void isAdmin(String username) {
        UserTable userTable = userRepository.getAdmin(username,"ADMIN");
        if(userTable == null){
            log.warn("Only admin can access this API");
            throw new EnterValidCredentialException(ResultInfoConstants.ACCES_DENIED);
        }
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

    public List<Helps> getHelpReply(String username) {
        isAdmin(username);
        List<Helps> helpTable = helpRepository.findAll()
                .stream()
                .map(HelpTable::toHelps)
                .collect(Collectors.toList());

        if(helpTable.isEmpty()){
            log.warn("Your chat is empty");
            throw new EnterValidCredentialException(ResultInfoConstants.EMPTY_CHAT);
        }

        return helpTable;
    }

    public void postHelpAnswer(Help help,String username,long id){
        isAdmin(username);
        List<HelpTable> helpTable = helpRepository.getByQuestionId(id);
        if(helpTable.isEmpty()){
            log.warn("No question found in this id");
            throw new EnterValidCredentialException(ResultInfoConstants.WRONG_ID);
        }
        Optional<HelpTable> optionalHelpTable = helpRepository.findById(id);
        HelpTable newHelp = help.toHelpTable();
        newHelp.setId(optionalHelpTable.get().getId());
        newHelp.setCourseId(optionalHelpTable.get().getCourseId());
        newHelp.setQuestion(optionalHelpTable.get().getQuestion());
        newHelp.setUsername(optionalHelpTable.get().getUsername());
        helpRepository.save(newHelp);

    }

    public void insertInstructor(Instructor instructor, String username) {
        isAdmin(username);
        String savedInstructor = instructor.getInstructor();
        InstructorTable instructorTable = instructorRepository.getByInstructor(savedInstructor);
        if (instructorTable != null) {
            log.warn("Instructor already exists");
            throw new EnterValidCredentialException(ResultInfoConstants.INSTRUCTOR_ALREADY_EXISTS);
        }
        instructorRepository.save(instructor.toInstructorTable());
    }
}

