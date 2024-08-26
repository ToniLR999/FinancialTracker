import { Component, ElementRef, AfterViewInit, ViewChild, OnInit } from '@angular/core';
import { Chart, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend, LineController } from 'chart.js';



@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit, AfterViewInit  {
  @ViewChild('myChart', { static: false }) myChart!: ElementRef<HTMLCanvasElement>;


  constructor(){
    Chart.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend, LineController);


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
            label: 'Income',
            data: [12, 19, 3, 5, 2, 3],
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
            fill: false, // No llenar el área debajo de la línea
            pointBackgroundColor: 'rgba(75, 192, 192, 1)', // Color de los puntos
            tension: 0.1 // Suaviza la línea
          },
          {
            label: 'Expenses',
            data: [10, 22, 1, 4, 2, 0 ],
            borderColor: 'rgba(75, 192, 192, 1)',
            borderWidth: 1,
            fill: false, // No llenar el área debajo de la línea
            pointBackgroundColor: 'rgba(75, 192, 192, 1)', // Color de los puntos
            tension: 0.1 // Suaviza la línea
          }
        
        ]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          },
          plugins: {
            legend: {  // Aquí se configura la leyenda
              display: true,  // Mostrar la leyenda
              position: 'top',  // Posición de la leyenda
              labels: {
                color: 'rgb(255, 255, 255)',  // Color del texto de la leyenda
                font: {
                  size: 14,  // Tamaño de la fuente de la leyenda
                  family: 'Arial',  // Familia de la fuente
                  style: 'italic'  // Estilo de la fuente
                },
                boxWidth: 20,  // Ancho de la caja de color de la leyenda
                padding: 10  // Espaciado entre elementos de la leyenda
              }
            },
            tooltip: {
              enabled: true  // Habilitar tooltips
            }
          }
        }
      });
    } else {
      console.error('No se pudo obtener el contexto 2D del canvas');
    }  }

}