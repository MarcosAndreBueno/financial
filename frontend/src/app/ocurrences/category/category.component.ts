import { Category } from './../model/category';
import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  
  categories$: Observable<any>;

  //injetação categoryService
  constructor(private categoryService: CategoryService) { 
    this.categories$ = this.categoryService.list();
  }
}
