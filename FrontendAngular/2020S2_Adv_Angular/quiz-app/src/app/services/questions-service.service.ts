import { Injectable } from '@angular/core';
import { Choice } from '../Datamodel/Choices';
import { Question } from '../Datamodel/Question';
import { Observable, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QuestionsServiceService {

  constructor(private http:HttpClient) { }
  // fetchQuestions():Observable<Question>{
  //   // var defaultChoices = [
  //   //   new Choice("this is a valid choice", true),
  //   //   new Choice("this is an invalid choice", false)
  //   // ];
  //  // var currentQuestion = new Question("Default question title", defaultChoices);
  //    return this.http.get<Question>("http://localhost:10080/quiz-rest/rest/quizzes/questions/1");
 
  // }
}
