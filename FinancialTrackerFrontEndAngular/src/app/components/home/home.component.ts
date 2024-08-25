import { Component, ElementRef, AfterViewInit, ViewChild, OnInit } from '@angular/core';
import { Chart, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js';



@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit, AfterViewInit  {
  @ViewChild('myChart', { static: false }) myChart!: ElementRef<HTMLCanvasElement>;


  constructor(){
    Chart.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);


  }
  
  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    const canvas = this.myChart.nativeElement;
    const ctx = canvas.getContext('2d');
  
    console.log('ViewChild:', this.myChart, ctx); // Debería mostrar información sobre el canvas
  
    
    if (ctx) {
      new Chart(ctx, {
        type: 'line',
        data: {
          labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
          datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
            fill: false, // No llenar el área debajo de la línea
            pointBackgroundColor: 'rgba(75, 192, 192, 1)', // Color de los puntos
            tension: 0.1 // Suaviza la línea
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
    }  }

}