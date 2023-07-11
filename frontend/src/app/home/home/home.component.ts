import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(
    private router: Router,
    private currentRoute: ActivatedRoute
  ) {}
  goToIncomes() {
    this.router.navigate(['occurrences/incomes'])
  }

  goToOutcomes() {
    this.router.navigate(['occurrences/outcomes'])
  }
}
