import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quiz-portal',
  templateUrl: './quiz-portal.component.html',
  styleUrls: ['./quiz-portal.component.css']
})
export class QuizPortalComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  redirectToTeacher() {
    this.router.navigate(['Teacher']);
  }
    redirectToStudent() {
    this.router.navigate(['Student']);
  }

}
