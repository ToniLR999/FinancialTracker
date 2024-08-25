import { Component, ElementRef, AfterViewInit, ViewChild, AfterViewChecked } from '@angular/core';
import { Chart } from "chart.js";


@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements AfterViewInit, AfterViewChecked  {
  @ViewChild('myChart', { static: false }) myChart!: ElementRef<HTMLCanvasElement>;

  chartInitialized = false;

  ngAfterViewInit(){
    setTimeout(() => {
      console.log('ViewChild after timeout:', this.myChart);
      this.initializeChart();
    }, 0);
    console.log('ViewChild:', this.myChart); // Debería mostrar información sobre el canvas


    if (this.myChart) {
      this.initializeChart();
    } else {
      console.error('Canvas not found');
    }
       


}
  constructor() { }
  ngAfterViewChecked(): void {
    console.log('ngAfterViewChecked:', this.myChart); // Aquí debería estar definido
    if (this.myChart && !this.chartInitialized) {
      this.chartInitialized = true;
      this.initializeChart();
    }  }


initializeChart() {
  const canvas = this.myChart.nativeElement;
  const ctx = canvas.getContext('2d');

  if (ctx) {
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  } else {
    console.error('No se pudo obtener el contexto 2D del canvas');
  }

}
}