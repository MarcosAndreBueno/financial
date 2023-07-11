import { Component } from '@angular/core';
import { OccurrenceService } from './services/occurrence.service';
import { CategoryService } from './services/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { Observable } from 'rxjs';
import { Occurrence } from './model/occurrence';

@Component({
  selector: 'app-occurrences',
  templateUrl: './occurrences.component.html',
  styleUrls: ['./occurrences.component.css']
})
export class OccurrencesComponent {
  occurrences$: Observable<Occurrence[]>;

  bsConfig?: Partial<BsDatepickerConfig>;
  selectedDate = new Date();

  constructor(
    private categoryService: CategoryService,
    private occurrenceService: OccurrenceService,
    private router: Router,
    private currentRoute: ActivatedRoute,
  ) {
    this.occurrences$ = this.occurrenceService.list(
      this.selectedDate.getMonth() + 1, this.selectedDate.getFullYear()
    );

    this.bsConfig = Object.assign({}, {
      containerClass: 'theme-dark-blue',
      dateInputFormat: 'MM/YYYY'
    });
  }

  onAdd() {
    //relativa à rota atual
    this.router.navigate(['new-occurrence'], { relativeTo: this.currentRoute })
  }

  onUpdate(occurrence: Occurrence) {
    this.router.navigate(['update', occurrence.id], { relativeTo: this.currentRoute })
  }

  onDelete(occurrence: Occurrence) {
    this.occurrenceService.deleteById(occurrence.id).subscribe(
      () => {
        console.log("Exclusão bem sucedida!");
        this.refresh()
      },
      (error) => {
        console.log("Erro ao excluir: ", error);
      })
  }

  refresh() {
    this.occurrences$ = this.occurrenceService.list(
      this.selectedDate.getMonth() + 1, this.selectedDate.getFullYear()
    );
  }

  return() {
    this.router.navigate(['home'])
  }

  //month and year only
  onOpenCalendar(container: any) {
    container.monthSelectHandler = (event: any): void => {
      container._store.dispatch(container._actions.select(event.date));
    };
    container.setViewMode('month');
  }

  increaseMonth() {
    this.selectedDate = new Date(this.selectedDate.getFullYear(),
      this.selectedDate.getMonth() + 1, this.selectedDate.getDate());
    this.refresh()
  }

  decreaseMonth() {
    this.selectedDate = new Date(this.selectedDate.getFullYear(),
      this.selectedDate.getMonth() - 1, this.selectedDate.getDate());
    this.refresh()
  }
}
