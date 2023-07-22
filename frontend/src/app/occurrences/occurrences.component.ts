import { Component, OnInit } from '@angular/core';
import { OccurrenceService } from './services/occurrence.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { Observable, map } from 'rxjs';
import { Occurrence } from './model/occurrence';

@Component({
  selector: 'app-occurrences',
  templateUrl: './occurrences.component.html',
  styleUrls: ['./occurrences.component.css']
})
export class OccurrencesComponent implements OnInit {

  occurrences$!: Observable<Occurrence[]>;
  totalAmount$!: Observable<number>;
  bsConfig?: Partial<BsDatepickerConfig>;
  selectedDate = new Date();

  constructor(
    private occurrenceService: OccurrenceService,
    private router: Router,
    private currentRoute: ActivatedRoute,
  ) {
    //datepicker config
    this.bsConfig = Object.assign({}, {
      containerClass: 'theme-dark-blue',
      dateInputFormat: 'MM/YYYY'
    });
  }

  ngOnInit(): void {
    //get occurrences
    this.refresh()
  }

  onAdd() {
    //relativa à rota atual
    this.router.navigate(['new-occurrence'], { relativeTo: this.currentRoute })
  }

  onUpdate(occurrence: Occurrence) {
    this.router.navigate(['update', occurrence.id], { relativeTo: this.currentRoute })
  }

  onDelete(occurrence: Occurrence) {
    if(confirm('Are you sure you want to delete?'))
    this.occurrenceService.deleteById(occurrence.id).subscribe(
      () => {
        alert("Occurrence deleted")
        this.refresh()
      })
  }

  refresh() {
    this.occurrences$ = this.occurrenceService.list(
      this.selectedDate.getMonth() + 1, this.selectedDate.getFullYear()
    );
    this.totalAmount$ = this.occurrences$.pipe(
      map(occurrences =>
        occurrences.map((occurrence =>
          parseFloat(occurrence.amount))
        )),
        map(amounts =>
          amounts.reduce((total: number, current: number) => total + current, 0)
        )
    )
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
