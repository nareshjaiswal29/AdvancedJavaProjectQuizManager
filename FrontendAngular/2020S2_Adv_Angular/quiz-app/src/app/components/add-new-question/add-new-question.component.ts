import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup,FormArray, Validators } from '@angular/forms';
import { AllApiServiceService } from 'src/app/all-api-service.service';
import { QuestionCl } from 'src/app/Datamodel/QuestionCl';

@Component({
  selector: 'app-add-new-question',
  templateUrl: './add-new-question.component.html',
  styleUrls: ['./add-new-question.component.css']
})
export class AddNewQuestionComponent implements OnInit {

  mode = 'quiz';
  submitted:boolean = false;
  registerForm:any;
  registerQuizForm:any;
  ddvalue: any;
  bValid1:boolean=false;
  bValid2:boolean=false;
  bValid3:boolean=false;
  bValid4:boolean=false;
  lstNews: any;
  QuizId:any;
  selectedLevel:any;
  myqDataforUpdate:any;
  Isupdate:any;

  UQustion:any;
  UOptionId1:any;
  UOptionValue1:any;
  UOptionId2:any;
  UOptionValue2:any;
  UOptionId3:any;
  UOptionValue3:any;
  UOptionId4:any;
  UOptionValue4:any;
  UTopics:any;
  UquestionId:any;
  constructor(private formbuilder: FormBuilder,private _Apiservice: AllApiServiceService) {
    
   }

  ngOnInit(): void {


      this.recDataFromService()
      console.log(this.myqDataforUpdate);
      if(typeof this.myqDataforUpdate == 'undefined')
      {

      this.getAll();
    this.registerForm = this.formbuilder.group({
      Question: ["", Validators.required],
      Option1: ["", Validators.required],
      Option2: ["", Validators.required],
      Option3: ["", Validators.required],
      Option4: ["", Validators.required],
      Topics: ["", Validators.required],
    })
    this.registerQuizForm = this.formbuilder.group(
      {
        QuizName:["",Validators.required]
      }
    )
  }
   else{
   
    this.Isupdate = 1;
    this.callme(this.myqDataforUpdate)
    this.mode= 'questionType';
    this.getAll();
    this.registerForm = this.formbuilder.group({
      Question: ["", Validators.required],
      Option1: ["", Validators.required],
      Option2: ["", Validators.required],
      Option3: ["", Validators.required],
      Option4: ["", Validators.required],
      Topics: ["", Validators.required],

    })

   }
  
  }

  onQuizSubmit(){
    if (this.registerQuizForm.invalid) return;
    const Quizdata = {
      "quizTitle": this.registerQuizForm.value.QuizName
    };
    this._Apiservice.AddQuizName(Quizdata)
    .subscribe(
      response => {
        console.log(response);
        this.QuizId = response;
      },
      error => {
        console.log(error);
      });
    //this.ContactService.(await this.createContact());
    //alert("Succsess Signup\n" + JSON.stringify(this.registerQuizForm.value));
    alert('QUIZ CREATED!');
    this.getAll();
    this.registerQuizForm.reset();
  }
  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) return;
    console.log("onsubmit");
   // this.contactService.saveBlog(this.createQuestionAndChoices());
   if(this.Isupdate !=1)
   {
    const data = {
      "questionTitle": this.registerForm.value.Question,
      "quizId":this.selectedLevel,
      "topics":this.registerForm.value.Topics,
      "choices": [
          {
              "choiceTitle": this.registerForm.value.Option1,
              "valid": this.bValid1
          },
          {
              "choiceTitle": this.registerForm.value.Option2,
              "valid": this.bValid2
          },
          {
              "choiceTitle": this.registerForm.value.Option3,
              "valid":this.bValid3
          },
          {
              "choiceTitle": this.registerForm.value.Option4,
              "valid":this.bValid4
          }
           
      ]
  };
  console.log(data);
    this._Apiservice.CreateQuestions(data).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        });
      //this.ContactService.(await this.createContact());
     // alert("Succsess Signup\n" + JSON.stringify(this.registerForm.value));
      alert('QUESTION CREATED!');
      this.onReset();
   }
   else
   {
    const Udata = {
      "questionTitle": this.registerForm.value.Question,
      "quizId":this.QuizId,
      "topics":this.registerForm.value.Topics,
      "questionId": this.UquestionId,
      "choices": [
          {
              "id": this.UOptionId1,
              "choiceTitle": this.registerForm.value.Option1,
              "valid": this.bValid1
          },
          {
              "id": this.UOptionId2,
              "choiceTitle": this.registerForm.value.Option2,
              "valid": this.bValid2
          },
          {
              "id": this.UOptionId3,
              "choiceTitle": this.registerForm.value.Option3,
              "valid":this.bValid3
          },
          {
              "id": this.UOptionId4,
              "choiceTitle": this.registerForm.value.Option4,
              "valid":this.bValid4
          }
           
      ]
  };
  console.log(Udata);
    this._Apiservice.UpdateQuestions(Udata).subscribe(
        response => {
          console.log(response);
        },
        error => {
          console.log(error);
        });
      //alert("Succsess Signup\n" + JSON.stringify(this.registerForm.value));
      this.onReset();
      alert('QUESTION UPDATED!');
   }
  
   // this.onReset();

  }

  createQuestionAndChoices() {
    console.log("in createJobProfile");
    var questionCl = QuestionCl;
    questionCl.Question = this.registerForm.value.Question;
    questionCl.option1 = this.registerForm.value.Option1;
    questionCl.option2 = this.registerForm.value.Option2;
    questionCl.option3 = this.registerForm.value.Option3;
    questionCl.option4 = this.registerForm.value.Option4;
    questionCl.quizId = this.registerForm.value.Option4;
    questionCl.topics = this.registerForm.value.Option4;
    console.log(questionCl);
    return questionCl;
  }
  getValue(event: { target: any; }) {
    this.ddvalue = event['target'].value;
    console.log(this.ddvalue);
    if (this.ddvalue=='Option1') {
      this.bValid1=true;

    } else if (this.ddvalue=='Option2') {
      this.bValid2=true;
    } else if (this.ddvalue=='Option3') {
      this.bValid3=true;
    } else {
      this.bValid4=true;
    }
  }

  Csvdownload(){
    this.mode='questionType'
    this.getAll();
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
  selected(event: { target: any; }){
    this.selectedLevel = event['target'].value;
    console.log(this.selectedLevel)
  }

  recDataFromService() {
    console.log('i m in recDataFromService');
    this.myqDataforUpdate = this._Apiservice.getYourData();
  }

  callme(data:any)
  {

    console.log(data.tData);
    console.log(data.tData.questionTitle);
    console.log(data.tData.choices[0].id);
    //console.log(data.tData.tData.questionTitle);
    this.UQustion = data.tData.questionTitle;
    this.UTopics = data.tData.topics;
    this.QuizId =  data.tData.quizId
    this.UquestionId = data.tData.questionId;
    this.UOptionId1 = data.tData.choices[0].id;
    this.UOptionValue1 = data.tData.choices[0].choiceTitle;

    this.UOptionId2 = data.tData.choices[1].id;
    this.UOptionValue2 = data.tData.choices[1].choiceTitle;

    this.UOptionId3 = data.tData.choices[2].id;
    this.UOptionValue3 = data.tData.choices[2].choiceTitle;

    this.UOptionId4 = data.tData.choices[3].id;
    this.UOptionValue4 = data.tData.choices[3].choiceTitle;
   
  }

  onReset() {
    //this.submitted = false;
    this.registerForm.reset();
    
  }
}
