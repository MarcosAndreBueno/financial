import { Component } from '@angular/core';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  
  categories: any[] = [];

  constructor() { 
    this.categories = [{ id: 1, category: 'Home' }];
  }
}
