package com.example.virtuallearn.Controller;

import com.example.virtuallearn.Repository.Table.HelpTable;
import com.example.virtuallearn.Repository.Table.NotificationTable;
import com.example.virtuallearn.UserAuthorisation.*;
import com.example.virtuallearn.Constants.ResultInfoConstants;
import com.example.virtuallearn.Controller.Response.ResponseWrapper;
import com.example.virtuallearn.Entity.*;
import com.example.virtuallearn.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Data
public class UserController {

    private final GetUser getUser;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTUtility jwtUtility;

    //Request and Resend OTP
    @PostMapping("/getotp")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> getotp(@RequestBody @Valid Otp otp) throws JsonProcessingException {
        log.info("Received getotp request", objectMapper.writeValueAsString(otp));
        return new ResponseWrapper(ResultInfoConstants.OTP_SENT, userService.getotp(otp));
    }

    //Signup
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> signUp(@RequestBody @Valid User user) throws JsonProcessingException {
        log.info("Received signup request {}", objectMapper.writeValueAsString(user));
        return new ResponseWrapper(ResultInfoConstants.SIGNUP_SUCCESS, userService.signUp(user));
    }

    //Update profile
    @PostMapping("/profile/{phonenumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> profile(@RequestBody @Valid User user, @PathVariable Long phonenumber) throws JsonProcessingException {
        log.info("Received profile update request {}", objectMapper.writeValueAsString(user));
        userService.profile(user, phonenumber);
        return new ResponseWrapper(ResultInfoConstants.PROFILE_UPDATED, true);
    }

    //edit profile
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> editProfile(@RequestBody @Valid User user, HttpServletRequest request) throws JsonProcessingException {
        log.info("Received profile edit request {}", objectMapper.writeValueAsString(user));
        userService.editProfile(user, getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.PROFILE_EDITED, true);
    }

    @GetMapping("/all/categories")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Category>> getAllCategories(Pageable page) {
        log.info("Received a request to get all Categories");
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, userService.getAllCategories(page));
    }

    @GetMapping("/all/courses")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Course>> getAllCourses(Pageable page) {
        log.info("Received a request to get all Courses");
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, userService.getAllCourses(page));
    }

    @GetMapping("/category/category_id/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Course>> getAllCoursesByCategory(@PathVariable long categoryId) {
        log.info("Received a request to get all courses by category : {}", categoryId);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, userService. getAllCoursesByCategory(categoryId));
    }
    //get overview by course
    @GetMapping("/overview/course_id/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Course>> getOverviewByCourse(@PathVariable long courseId){
        log.info("Received a request to overview by course : {}", courseId);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,userService.getOverviewByCourse(courseId));
    }


    @GetMapping({"/privacy_policy"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<PrivacyPolicy>> getPrivacyPolicy() throws JsonProcessingException {
        log.info("Received request to get privacy policy");
        return new ResponseWrapper(ResultInfoConstants.PRIVACY_POLICY,userService.getPrivacyPolicy());
    }

    @GetMapping({"/terms_of_services"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<TermsOfServices>> getTermsOfServices() throws  JsonProcessingException {
        log.info("Received request to get terms of services");
        return new ResponseWrapper(ResultInfoConstants.TERMS_OF_SERVICES,userService.getTermsOfServices());
    }

    @GetMapping({"/course_id/{courseId}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Chapter>> getByType(@PathVariable long courseId){
        log.info("Received a request to get all chapter of the course : {}", courseId);
        List<ChapterContent> chapters =userService.getByCourse(courseId);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,chapters);
    }

    //change password using current password
    @PostMapping("/privacy")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> privacy(@RequestBody @Valid User user, HttpServletRequest request) throws JsonProcessingException {
        log.info("Received profile update request {}", objectMapper.writeValueAsString(user));
        userService.privacy(user,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.PROFILE_UPDATED,true);
    }

    //forgot password
    @PostMapping("/forgot_password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> forgot(@RequestBody @Valid Otp otp) throws JsonProcessingException {
        log.info("Received forgot password request {}", objectMapper.writeValueAsString(otp));
        return new ResponseWrapper(ResultInfoConstants.OTP_SENT,userService.forgot(otp));
    }

    //reset password
    @PostMapping("/reset")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> reset(@RequestBody @Valid User user) throws JsonProcessingException {
        log.info("Received reset password request {}", objectMapper.writeValueAsString(user));
        return new ResponseWrapper(ResultInfoConstants.RESET_PASSWORD_SUCCESS, userService.reset(user));
    }

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Course>> profile(HttpServletRequest request) {
        log.info("Received a request to get profile : {}", getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, userService.profile(getUser.getId(request)));
    }

    //display test questions
    @GetMapping({"/test"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Tests>> getTest(@RequestHeader long courseId,@RequestHeader long chapterId){
        log.info("Received a request to get questions of the chapter : {}", courseId);
        List<Tests> test =userService.getTest(courseId,chapterId);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,test);
    }

    //enter answers
    @PutMapping({"/test/id/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> takeTest(HttpServletRequest request,@RequestBody @Valid Result result,@PathVariable long id){
        log.info("Received a request to post answer of the question ");
        return new ResponseWrapper(ResultInfoConstants.ANSWER_SAVED,userService.takeTest(getUser.getId(request),result,id));
    }

    //show result
    @GetMapping({"/results"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Results>> getResult(HttpServletRequest request,@RequestHeader long courseId,@RequestHeader long chapterId) {
        log.info("Received a request to get result of the question : {}", courseId);
        List<Results> results = userService.getResult(getUser.getId(request), courseId, chapterId);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, results);
    }

    //join course
    @PostMapping({"/join"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> joinCourse(HttpServletRequest request, @RequestBody @Valid MyCourse myCourse){
        log.info("Received a request to join the course : {}",getUser.getId(request));
        userService.joinCourse(getUser.getId(request),myCourse);
        return new ResponseWrapper(ResultInfoConstants.COURSE_JOINED,true);
    }

    //display mycourses
    @GetMapping({"/mycourse"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<MyCourses>> mycourse(HttpServletRequest request) {
        log.info("Received a request to get all the chosen courses : {}", getUser.getId(request));
        List<MyCourses> myCourse = userService.mycourse(getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, myCourse);
    }

    @PostMapping("/login")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    ));
        }catch (BadCredentialsException badCredentialsException){
            throw new Exception("INVALID CREDENTIALS", badCredentialsException);
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        return new JWTResponse(token);
    }

    @PostMapping({"/logout"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> logout(@RequestBody @Valid Blacklist blacklist){
        log.info("Logout success : {}",blacklist);
        userService.logout(blacklist);
        return new ResponseWrapper(ResultInfoConstants.LOGOUT,true);
    }

    @GetMapping({"/mycourse/ongoing"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Ongoing>> ongoing(HttpServletRequest request) {
        log.info("Received a request to get ongoing courses : {}", getUser.getId(request));
        List<Ongoing> myCourse = userService.ongoing(getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, myCourse);
    }

    @GetMapping({"/mycourse/completed"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Ongoing>> completed(HttpServletRequest request) {
        log.info("Received a request to get completed courses : {}", getUser.getId(request));
        List<Ongoing> myCourse = userService.completed(getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS, myCourse);
    }

    @PostMapping({"/help"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> help(@RequestBody @Valid Help help,HttpServletRequest request){
        log.info("Received help request to answer the doubts on related course : {}",help);
        userService.postHelpQuestion(help,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,true);
    }

    @GetMapping({"/help/reply"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Helps>> getByContent(HttpServletRequest request){
        log.info("Received a request to get reply to posted question : {}",getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,userService.getHelpReply(getUser.getId(request)));
    }

    @GetMapping({"/mark"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List> getMark(HttpServletRequest request,@RequestHeader long courseId,@RequestHeader long chapterId){
        log.info("Received a request to get marks: {}",getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,userService.getMark(getUser.getId(request),courseId,chapterId));
    }

    @GetMapping({"/notification"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Notifications> getNotification(HttpServletRequest request){
        log.info("Received a request to get notifications : {}",getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,userService.getNotification(getUser.getId(request)));
    }
}
