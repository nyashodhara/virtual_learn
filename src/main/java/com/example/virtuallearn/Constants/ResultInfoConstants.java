package com.example.virtuallearn.Constants;


import com.example.virtuallearn.Controller.Response.ResultInfo;
import lombok.Data;

@Data
public class ResultInfoConstants {
    public static final ResultInfo SUCCESS=new ResultInfo("Requested operation completed successfully");
    public static final ResultInfo SIGNUP_SUCCESS=new ResultInfo("sign Up successfully completed");
    public static final ResultInfo OTP_SENT=new ResultInfo("otp sent to the phone number");
    public static final ResultInfo INCORRECT_PASSWORD=new ResultInfo("Incorrect password");
    public static final ResultInfo PHONE_NUMBER_ALREADY_PRESENT=new ResultInfo("Requested phone number already registered try login");
    public static final ResultInfo OTP_ALREADY_SENT=new ResultInfo("otp already sent to this phone number");
    public static final ResultInfo ENTER_VALID_PHONE_NUMBER=new ResultInfo("enter 10 digit mobile number");
    public static final ResultInfo WRONG_OTP=new ResultInfo("entered otp is wrong please enter the correct otp ");
    public static final ResultInfo REQUEST_OTP=new ResultInfo("first request the otp");
    public static final ResultInfo NO_ACCOUNT_FOUND=new ResultInfo("There is no account in this number");
    public static final ResultInfo PROFILE_UPDATED=new ResultInfo("profile updated successfully");
    public static final ResultInfo ENTER_VALID_PASSWORD=new ResultInfo("password length must between 6 and 16 character,Password must contain atleast one upper case atleast one lower case and atleast one digit");
    public static final ResultInfo CATEGORY_ADDED=new ResultInfo("Category added successfully");
    public static final ResultInfo COURSE_ADDED=new ResultInfo("Course added successfully");
    public static final ResultInfo CATEGORY_ALREADY_PRESENT=new ResultInfo("Category already present");
    public static final ResultInfo NO_SUCH_CATEGORY=new ResultInfo("no such category present");
    public static final ResultInfo COURSE_ALREADY_PRESENT=new ResultInfo("Course already present");
    public static final ResultInfo PHONE_NUMBER_NOT_EXIST=new ResultInfo("phone number doesn't exist");
    public static final ResultInfo RESET_PASSWORD_SUCCESS=new ResultInfo("Reset password successful");
    public static final ResultInfo PROFILE_EDITED = new ResultInfo("Profile edited successfully");
    public static final ResultInfo NO_SUCH_COURSE = new ResultInfo("NO_SUCH_COURSE");
    public static final ResultInfo CHAPTER_ALREADY_EXISTS = new ResultInfo("CHAPTER_ALREADY_EXISTS ");
    public static final ResultInfo CHAPTER_ADDED = new ResultInfo("CHAPTER_ADDED");
    public static final ResultInfo PRIVACY_POLICY_EXISTS = new ResultInfo("PRIVACY_POLICY_EXISTS");
    public static final ResultInfo PRIVACY_POLICY_AND_TERMS_AND_SERVICES_ADDED = new ResultInfo("PRIVACY_POLICY_AND_TERMS_AND_SERVICES_ADDED ");
    public static final ResultInfo PRIVACY_POLICY_ALREADY_EXISTS = new ResultInfo("PRIVACY_POLICY_ALREADY_EXISTS") ;
    public static final ResultInfo PRIVACY_POLICY_ADDED = new ResultInfo("PRIVACY_POLICY_ADDED");
    public static final ResultInfo PRIVACY_POLICY = new ResultInfo("PRIVACY_POLICY");
    public static final ResultInfo TERMS_OF_SERVICES_ALREADY_EXISTS = new ResultInfo("TERMS_OF_SERVICES_ALREADY_EXISTS");
    public static final ResultInfo TERMS_OF_SERVICES = new ResultInfo("TERMS_OF_SERVICES");
    public static final ResultInfo TERMS_OF_SERVICES_ADDED = new ResultInfo("TERMS_OF_SERVICES_ADDED");
    public static final ResultInfo NO_SUCH_COURSE_AND_CHAPTER_EXISTS = new ResultInfo("there is no course/chapter in this id");
    public static final ResultInfo CONTENT_ADDED = new ResultInfo("CONTENT_ADDED");
    public static final ResultInfo CONTENT_ALREADY_EXISTS = new ResultInfo("These contents are already present");
    public static final ResultInfo TEST_ADDED = new ResultInfo("TEST_ADDED");
    public static final ResultInfo QUESTION_ALREADY_EXISTS = new ResultInfo("question already exists in this chapter");
    public static final ResultInfo INVALID_INPUT = new ResultInfo("Invalid input");
    public static final ResultInfo ALREADY_ANSWERED = new ResultInfo("This question already answered");
    public static final ResultInfo ANSWER_SAVED = new ResultInfo("Your answer saved successfully");
    public static final ResultInfo COURSE_JOINED = new ResultInfo("course joined successfully");
    public static final ResultInfo EMPTY_MYCOURSE = new ResultInfo("mycourse is empty");
    public static final ResultInfo JOIN_COURSE = new ResultInfo("First join course to write test");
    public static final ResultInfo USERNAME_DUPLICATE = new ResultInfo("username already present use unique username");
    public static final ResultInfo ALREADY_JOINED = new ResultInfo("you already joined the course");
    public static final ResultInfo LOGOUT = new ResultInfo("Logout success");
    public static final ResultInfo ALREADY_BLACKLIST = new ResultInfo("Token already in blacklist");
    public static final ResultInfo ENTER_VALID_GENDER = new ResultInfo("ENTER_VALID_GENDER");
    public static final ResultInfo EMPTY_CHAPTER = new ResultInfo("There is no course in this id");
    public static final ResultInfo WRONG_ID = new ResultInfo("No question present for this id");
    public static final ResultInfo ACCES_DENIED = new ResultInfo("Only Admin can access this API");
    public static final ResultInfo EMPTY_ONGOING = new ResultInfo("There is no ongoing/completed courses");;;
    public static final ResultInfo EMPTY_CHAT = new ResultInfo("Your chat is empty");
    public static final ResultInfo COURSE_IS_ALREADY_ASSIGNED_WITH_INSTRUCTOR = new ResultInfo("COURSE_IS_ALREADY_ASSIGNED_WITH_INSTRUCTOR") ;
    public static final ResultInfo EMPTY_NOTIFICATION = new ResultInfo("Your Notification is empty");;
}



