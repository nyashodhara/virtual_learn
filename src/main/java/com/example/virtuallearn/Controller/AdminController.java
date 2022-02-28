package com.example.virtuallearn.Controller;

import com.example.virtuallearn.Constants.ResultInfoConstants;
import com.example.virtuallearn.Controller.Response.ResponseWrapper;
import com.example.virtuallearn.Entity.*;
import com.example.virtuallearn.Service.AdminService;
import com.example.virtuallearn.Service.UserService;
import com.example.virtuallearn.UserAuthorisation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/admin")
@Slf4j
@Data
public class AdminController {

    private final GetUser getUser;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;

    private final JWTUtility jwtUtility;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Long> signUp(@RequestBody @Valid User user) throws JsonProcessingException {
        log.info("received signup request {}", objectMapper.writeValueAsString(user));
        return new ResponseWrapper(ResultInfoConstants.SIGNUP_SUCCESS, adminService.signUp(user));
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

    @PostMapping("/insert_category")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insert(@RequestBody @Valid Category category, HttpServletRequest request) throws JsonProcessingException {
        log.info("Received category insert request {}", objectMapper.writeValueAsString(category));
        adminService.insert(category, getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.CATEGORY_ADDED,true);
    }

    @PostMapping("/insert_course")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insertCourse(@RequestBody @Valid Course course,HttpServletRequest request) throws JsonProcessingException {
        log.info("Received course insert request {}", objectMapper.writeValueAsString(course));
        adminService.insertCourse(course,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.COURSE_ADDED,true);
    }

    @PostMapping("/insert_chapter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insertChapter(@RequestBody @Valid Chapter chapter,HttpServletRequest request) throws JsonProcessingException {
        log.info("Received chapter insert request {}", objectMapper.writeValueAsString(chapter));
        adminService.insertChapter(chapter,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.CHAPTER_ADDED,true);
    }

    @PostMapping("/insert_policy")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insertPolicy(@RequestBody @Valid PrivacyPolicy privacyPolicy,HttpServletRequest request) throws JsonProcessingException {
        log.info("Received request to insert privacy policy{}", objectMapper.writeValueAsString(privacyPolicy));
        adminService.insertPrivacyPolicy(privacyPolicy,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.PRIVACY_POLICY_ADDED, true);
    }

    @PostMapping("/insert_terms")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insertTerms(@RequestBody @Valid TermsOfServices termsOfServices,HttpServletRequest request) throws JsonProcessingException {
        log.info("Received request to insert terms of services{}", objectMapper.writeValueAsString(termsOfServices));
        adminService.insertTermsOfService(termsOfServices,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.TERMS_OF_SERVICES_ADDED, true);
    }

    @PostMapping("/insert_content")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insertContent(@RequestBody @Valid ChapterContent chapterContent,HttpServletRequest request) throws JsonProcessingException {
        log.info("Received request to insert content of the chapter", objectMapper.writeValueAsString(chapterContent));
        adminService.insertContent(chapterContent,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.CONTENT_ADDED, true);
    }

    //insert test questions
    @PostMapping("/insert_test")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insertTest(@RequestBody @Valid Test test,HttpServletRequest request) throws JsonProcessingException {
        log.info("Received request to insert test of the chapter", objectMapper.writeValueAsString(test));
        adminService.insertTest(test,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.TEST_ADDED, true);
    }

    @PostMapping({"/logout"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> logout(@RequestBody @Valid Blacklist blacklist){
        log.info("Logout success : {}",blacklist);
        adminService.logout(blacklist);
        return new ResponseWrapper(ResultInfoConstants.LOGOUT,true);
    }

    @GetMapping({"/help"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<List<Helps>> getHelpReply(HttpServletRequest request){
        log.info("Received a request to get doubt answers : {}",getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,adminService.getHelpReply(getUser.getId(request)));
    }

    @PutMapping({"/help/reply/id/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> help(@RequestBody @Valid Help help,HttpServletRequest request,@PathVariable long id){
        log.info("Received help request to answer the doubts on related course : {}",help);
        adminService.postHelpAnswer(help,getUser.getId(request),id);
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,true);
    }

    @PostMapping("/insert_instructor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<Boolean> insertInstructor(@RequestBody @Valid Instructor instructor, HttpServletRequest request) throws JsonProcessingException {
        log.info("Received instructor insert request {}", objectMapper.writeValueAsString(instructor));
        adminService.insertInstructor(instructor,getUser.getId(request));
        return new ResponseWrapper(ResultInfoConstants.SUCCESS,true);
    }
}
