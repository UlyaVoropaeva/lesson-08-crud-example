import { Component, OnInit } from '@angular/core';
import {Student} from "../service/student";
import {StudentService} from "../service/student.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-student-form',
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.html']
})
export class StudentFormComponent implements OnInit {

  public student = new Student(null, "", "", "");

  constructor(private studentService: StudentService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param["id"] === "new") {
        this.student = new Student(null, "", "", "");
      }
      this.studentService.findById(param["id"])
        .subscribe(student => {
          this.student = student;
        }, error => {
          console.log(error);
        })
    })
  }

}
