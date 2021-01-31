import { Component, OnInit,CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  redirectToStudent() {
    console.log('hello')
    console.log(this.router.navigate(['Student']));
    this.router.navigate(['./StartExamComponent']);
  }

  redirectToTeacher() {
    console.log('hello')
    console.log(this.router.navigate(['Student']));
    this.router.navigate(['./Teacher']);
  }

}
