import { Component, OnInit } from '@angular/core';
import { AnalysisService } from 'src/app/occurrences/services/analysis.service';
import { getLocaleDateFormat, Location } from '@angular/common';

@Component({
  selector: 'app-analysis',
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.css']
})
export class AnalysisComponent implements OnInit {

  public analysisList: { category: string; value: number }[] = [];
  public mesAtual: String = '';

  constructor(
    private analysisService: AnalysisService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.initData();
  }

  private initData() {
    const monthNames = [
      "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    ];
    const now = new Date();
    const monthIndex = now.getMonth();
    const monthName = monthNames[monthIndex];
    this.mesAtual = monthName;

    this.analysisService.getCategoryExpenseIncreaseLastMonth()
      .subscribe((data) => {
        console.log(data),
        this.analysisList = Object.entries(data).map(([category, value]) => ({
          category,
          value
        }));
      });
  }

  return() {
    this.location.back();
  }
}
