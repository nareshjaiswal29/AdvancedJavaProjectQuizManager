import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {TeacherComponent} from '../app/components/teacher/teacher.component'
import {StudentComponent} from '../app/components/student/student.component'
import {DashBoardComponent} from '../app/components/dash-board/dash-board.component'
import { AddNewQuestionComponent } from '../app/components/add-new-question/add-new-question.component'
import { UpdateQuestionComponent } from '../app/components/update-question/update-question.component'
import { QuetionListComponent } from '../app/components/quetion-list/quetion-list.component'
import { StartExamComponent } from '../app/components/start-exam/start-exam.component'
const routes: Routes = [
  { path: 'Teacher', component: TeacherComponent },
  { path: 'Student', component: StudentComponent },
  { path: 'DashboardHeader', component: DashBoardComponent },
  { path: 'AddNewQuestionComponent', component: AddNewQuestionComponent },
  { path: 'UpdateQuestionComponent', component: UpdateQuestionComponent },
  { path: 'DashBoardComponent', component: DashBoardComponent },
  { path: 'QuetionListComponent', component:  QuetionListComponent},
  { path: 'StartExamComponent', component:  StartExamComponent},
  { path: '', component: DashBoardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [TeacherComponent, StudentComponent, DashBoardComponent, AddNewQuestionComponent, UpdateQuestionComponent, QuetionListComponent,StartExamComponent]
