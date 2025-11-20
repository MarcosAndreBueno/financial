import { Component, OnInit } from '@angular/core';
import { OccurrenceService } from './services/occurrence.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { Observable, map, toArray } from 'rxjs';
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

  //organize ocurrences date on template
  renderDate: boolean[] = [];
  dateName: string[] = [];

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
    ).pipe( //sort date desc
      map(occurrences => occurrences.map(occurrence => ({
        ...occurrence,
        sortDate: new Date(occurrence.date).getTime()
      })).sort((a, b) => b.sortDate - a.sortDate))
    );

    //today date
    var todayDate = 
    this.selectedDate.getFullYear().toString() + '-' +
    (this.selectedDate.getMonth() + 1).toString().padStart(2, '0') + '-' +
    this.selectedDate.getDate().toString().padStart(2, '0');

    //render occurrence day
    const appearedDates: Set<string> = new Set();

    //reset
    this.renderDate = [];
    this.dateName = [];

    this.occurrences$.forEach(occurrences => 
      occurrences.forEach(occurrence => {
      const currDate = occurrence.date;
    
      if (appearedDates.has(currDate)) {
        this.renderDate.push(false); // don't render
        this.dateName.push("");
      } else {
        appearedDates.add(currDate);
        this.renderDate.push(true); // render
        if (currDate === todayDate) {
          this.dateName.push("Hoje");
        } else {
          this.dateName.push(currDate.slice(8, currDate.length));
        }
      }
    }));

    //sum occurrence amount
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
