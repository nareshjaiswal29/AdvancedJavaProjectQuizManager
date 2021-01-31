import{ Choice } from './Choices'
 
export class Question {
    questionTitle:string;
    answered:boolean=false;
    choices :Choice[] = new Array();
    questionId : number;
    quizId : string;

    constructor(questionTitle:string, choices:Choice[], questionId : number,quizId:string){
        this.questionTitle = questionTitle;
        this.choices = choices;
        this.quizId= quizId;
        this.questionId= questionId;
    }

}