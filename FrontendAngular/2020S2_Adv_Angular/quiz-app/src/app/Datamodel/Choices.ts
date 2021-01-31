export class Choice{
    id!:number
    choiceTitle !:string;
    valid !:boolean;
    selected: boolean;

    constructor(id:number, choiceTitle : string,valid:boolean,selected: boolean){
        this.id = id;
        this.choiceTitle = choiceTitle;
        this.valid = valid;
        this.selected=selected;
    }
}