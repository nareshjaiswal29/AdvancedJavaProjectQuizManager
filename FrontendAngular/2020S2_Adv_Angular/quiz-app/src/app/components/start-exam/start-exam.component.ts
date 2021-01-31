import { Component, OnInit } from '@angular/core';
import { AllApiServiceService } from 'src/app/all-api-service.service';
import { Question } from 'src/app/Datamodel/Question';
import {Choice} from 'src/app/Datamodel/Choices';
import { FormBuilder,FormGroup,FormArray, Validators } from '@angular/forms';

@Component({
  selector: 'app-start-exam',
  templateUrl: './start-exam.component.html',
  styleUrls: ['./start-exam.component.css']
})
export class StartExamComponent implements OnInit {

  mode:any = 'Startquiz';
  currentQuestion :any;
  submitted:boolean = false;
  registerStudentForm:any;
  Nanswers :any;
  studentId:any;
  lstNews: any;
  selectedLevel:any;
  //selectedState:any = '0';
 // currentQuestion: Question = new Question('default',[]);
  constructor(private _Apiservice: AllApiServiceService, private formbuilder: FormBuilder ){
    //TODO : put this in a list of question-edition-component
    // _Apiservice.fetchQuestions().subscribe(data => {
    //    console.log(data);
    //     this.currentQuestion = data;
    // })
  }
  
  SearchById(){
    this.fetchAllQuestions(this._Apiservice);
    //this.selectedState = '1';
    //console.log(this.selectedState);
  }

  fetchAllQuestions(_Apiservice: AllApiServiceService){
    _Apiservice.fetchQuestionsByQuizId(this.selectedLevel).subscribe(data => {
      console.log(data);
       this.currentQuestion = data;
   })
   }

  ngOnInit(): void {
    this.getAll();
    this.mode = "Student";
    //this.selectedState = 0;
    //console.log(this.selectedState);
    this.registerStudentForm = this.formbuilder.group(
      {
        StudentName:["",Validators.required]
      }
    )
  }

  onSelect(question: Question, option: Choice, ans:any) {
      question.choices.forEach((x) => { if (x.id != option.id) x.selected = false; });
     // Nanswers:any;
     console.log(ans)
      const dd = {
        'questionId': question.questionId,
        'studenId': this.studentId, 
        'answer': ans,
        'quizId':question.quizId
      };
      //this.currentQuestion.forEach((x: { questionId: any;answered:any }) => answers.push({ 'questionId': x.questionId,'studenId':'102', 'answer': x.answered,'quizId':'10' }));
      //console.log(this.currentQuestion);
      console.log(dd);
       this._Apiservice.SaveAnswers(dd)
       .subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        });
      //console.log(x);
    }

    isCorrect(question: Question) {
      return question.choices.every(x => x.selected == x.valid) ? 'correct' : 'wrong';
    };

    onSubmit() {
      let answers:any= [];
      this.currentQuestion.forEach((x: { questionId: any;answered:any }) => answers.push({ 'questionId': x.questionId,'studenId':'102', 'answer': x.answered,'quizId':'10' }));
      console.log(this.currentQuestion);
      console.log(answers);
      this._Apiservice.SaveAnswers(answers)
      .subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        });
      // Post your data to the server here. answers contains the questionId and the users' answer.
      //console.log(this.quiz.questions);
      //this.mode = 'result';
    }

    Submit(){
      //this.onSubmit();
      this.mode='quizResult'
      //this.getAll();
    }

    onStudentSubmit(){
      if (this.registerStudentForm.invalid) return;
      const Studentdata = {
        'studentName': this.registerStudentForm.value.StudentName
      };
      console.log(Studentdata);
      this._Apiservice.AddStudentName(Studentdata)
      .subscribe(
        response => {
          console.log(response);
          this.studentId = response;
        },
        error => {
          console.log(error);
        });
      //this.ContactService.(await this.createContact());
      alert("Succsess Signup\n" + JSON.stringify(this.registerStudentForm.value));
      this.mode = "Startquiz";
      
    }

    isAnswered(question: Question) {
      return question.choices.find(x => x.selected) ? 'Answered' : 'Not Answered';
    };

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

    selected(event: { target: any; }){
      this.selectedLevel = event['target'].value;
      console.log(this.selectedLevel)

      this.SearchById();
    }

}
