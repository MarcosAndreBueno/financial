import { Category } from './../model/category';
import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  
  categories$: Observable<Category[]>;

  constructor(
    private categoryService: CategoryService, //injeção categoryService
    private router: Router, //controler roteamento angular
    private actualRoute: ActivatedRoute //rota atual
    ) { 
    this.categories$ = this.categoryService.list();
  }

  newCategory() {
    //relativa à rota atual 
    this.router.navigate(['new-occurrence'], {relativeTo: this.actualRoute})
  }
}
