import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  redirectToQuiz() {
    console.log('hello')
    console.log(this.router.navigate(['Student']));
    this.router.navigate(['./AddNewQuestionComponent']);
  }

  redirectToQuizList() {
    console.log('hello')
    console.log(this.router.navigate(['QuetionListComponent']));
    this.router.navigate(['./QuetionListComponent']);
  }
}
