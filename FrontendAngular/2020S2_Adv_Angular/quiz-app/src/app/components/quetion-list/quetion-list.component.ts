import { Component, OnInit } from '@angular/core';
import { AllApiServiceService } from 'src/app/all-api-service.service';
import { Question } from 'src/app/Datamodel/Question';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quetion-list',
  templateUrl: './quetion-list.component.html',
  styleUrls: ['./quetion-list.component.css']
})
export class QuetionListComponent implements OnInit {

   currentQuestion :any;
   selectedLevel:any;
   lstNews: any;
  // currentQuestion: Question = new Question('default',[]);
   constructor(private _Apiservice: AllApiServiceService , private router: Router){
     //TODO : put this in a list of question-edition-component
     //this.currentQuestion = this._Apiservice.fetchQuestions().subscribe();
    // this.fetchAllQuestions(_Apiservice);
   }

   fetchAllQuestions(_Apiservice: AllApiServiceService){
    _Apiservice.fetchQuestionsByQuizId(this.selectedLevel).subscribe(data => {
      console.log(data);
       this.currentQuestion = data;
   })
   }
  ngOnInit(): void {
    //this.fetchAllQuestions(this._Apiservice);
    this.getAll();
  }

  SearchById(){
    this.fetchAllQuestions(this._Apiservice);
  }
  sendtoupdate(data:any){

    if (confirm("Are you sure to Update")) {
    console.log(data);
    const tData = data;
    const obj = {tData};
    this._Apiservice.onPushTable(obj);
    console.log('heloo');
    this.router.navigate(['/AddNewQuestionComponent']);
    }
  }

  DeleteQuestion(qId:any){
    if (confirm("Are you sure to Delete")) {
    this._Apiservice.DeleteQuestions(qId).subscribe(
      response => {
        console.log(response);
      },
      error => {
        console.log(error);
      });
      alert('QUESTION DELETED!')
    this.fetchAllQuestions(this._Apiservice);
   
    }
  }

  selected(event: { target: any; }){
    this.selectedLevel = event['target'].value;
    console.log(this.selectedLevel)
    this.SearchById();
  }
  public getAll() {
    //this.isShown = ! this.isShown;
    this._Apiservice.getQuizName()
          .subscribe
          (
            (            data: any) => {
              this.lstNews = data;
              console.log(data);
            }
          )
  }
}
