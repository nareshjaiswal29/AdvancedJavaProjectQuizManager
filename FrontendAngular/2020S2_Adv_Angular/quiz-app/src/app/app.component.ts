import { Component } from '@angular/core';
import { Choice } from '../app/Datamodel/Choices';
import { Question } from '../app/Datamodel//Question';
import { QuestionsServiceService } from './services/questions-service.service';
import { AllApiServiceService } from './all-api-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'quiz-app';
  currentQuestion: Question = new Question('default',[],0,'',);


   //Question :any[];
   //currentQuestion: Question = new Question('default',[]);
   //currentQuestion: Question = new Question('default',[]);
   constructor(private _Apiservice: AllApiServiceService ){
     //TODO : put this in a list of question-edition-component
     _Apiservice.fetchQuestions().subscribe(data => {
         this.currentQuestion = data;
     })
   }

  savelist(){
  
  }
}
