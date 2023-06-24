import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  
  categories: any[] = [];

  //injetação categoryService
  constructor(private categoryService: CategoryService) { 
    this.categories = this.categoryService.list();
  }
}
