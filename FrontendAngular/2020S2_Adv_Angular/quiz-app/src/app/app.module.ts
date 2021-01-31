import { CommonModule, HashLocationStrategy, LocationStrategy } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule,CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule , routingComponents} from './app-routing.module';
import { AppComponent } from './app.component';
import { DashBoardComponent } from './components/dash-board/dash-board.component';
import { QuizPortalComponent } from './components/quiz-portal/quiz-portal.component';
import { QuetionListComponent } from './components/quetion-list/quetion-list.component'
import { TeacherComponent } from './components/teacher/teacher.component';
import { StudentComponent } from './components/student/student.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { AddNewQuestionComponent } from '../app/components/add-new-question/add-new-question.component';
import { HttpClientModule} from '@angular/common/http';
import { StartExamComponent } from './components/start-exam/start-exam.component';
import { UpdateQuestionComponent } from './components/update-question/update-question.component';
import { DashboardHeaderComponent } from './dashboard-header/dashboard-header.component';


@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    DashBoardComponent,
    QuizPortalComponent,
    TeacherComponent,
    StudentComponent,
    AddNewQuestionComponent,
    StartExamComponent,
    UpdateQuestionComponent,
    QuetionListComponent,
    DashboardHeaderComponent
  ],
  imports: [
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,CommonModule
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
]
})
export class AppModule { }
