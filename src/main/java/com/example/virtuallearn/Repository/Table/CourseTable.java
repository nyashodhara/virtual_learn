package com.example.virtuallearn.Repository.Table;

import com.example.virtuallearn.Entity.Category;
import com.example.virtuallearn.Entity.Course;
import com.example.virtuallearn.Entity.Courses;
import com.example.virtuallearn.Entity.Overview;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "course_tbl")
@Data
@NoArgsConstructor
public class CourseTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String course;
    private String overview;
    private String category;
    @Column(name = "category_id")
    private long categoryId;
    private String instructor;
    @Column(name = "instructor_id")
    private long instructorId;


    public CourseTable(long id,String course,String overview,String category,long categoryId, String instructor,long instructorId) {
        this.id = id;
        this.course = course;
        this.overview = overview;
        this.category = category;
        this.categoryId = categoryId;
        this.instructor = instructor;
        this.instructorId = instructorId;
    }

    public Course toCourse() {
        return new Course(this.id, this.course, this.overview,this.categoryId, this.category, this.instructorId, this.instructor);
    }

    public Courses toCourses() {
        return new Courses(this.id,this.course, this.category, this.instructor);
    }

    public Overview toOverview() {
        return new Overview(this.course, this.overview);
    }
}
