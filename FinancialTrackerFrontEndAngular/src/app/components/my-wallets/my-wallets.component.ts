import { AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { gsap } from 'gsap';


@Component({
  selector: 'my-wallets',
  templateUrl: './my-wallets.component.html',
  styleUrls: ['./my-wallets.component.css']
})
export class MyWalletsComponent implements OnInit, AfterViewInit {
  @ViewChild('cardsWrapper') cardsWrapper!: ElementRef; // Referencia al contenedor de tarjetas
  @ViewChild('arrowPrev') arrowPrev!: ElementRef;
  @ViewChild('arrowNext') arrowNext!: ElementRef;

  currentCard: number = 2; // Índice inicial de la tarjeta
  dir: number = 1; // Dirección inicial

  constructor(private renderer: Renderer2) { }
  ngAfterViewInit(): void {
    const arrowBtns = [this.arrowPrev.nativeElement, this.arrowNext.nativeElement];
  const cardBtns = this.cardsWrapper.nativeElement.querySelectorAll('.card');

  // Aplicar efectos y manejar clics para los botones de flecha
  arrowBtns.forEach((btn: HTMLElement, i: number) => {
    this.applyPointerEffect(btn, 'expo', '0 3px 4px #00000050');
    this.renderer.listen(btn, 'click', () => {
      this.dir = i === 0 ? 1 : -1;
      this.currentCard += i === 0 ? -1 : 1;
      this.currentCard = Math.min(4, Math.max(0, this.currentCard));
      this.moveCards(0.75);
    });
  });

  // Aplicar efectos y manejar clics para los botones de tarjeta
  cardBtns.forEach((btn: HTMLElement, i: number) => {
    this.applyPointerEffect(btn, 'power3', () =>
      i === this.currentCard ? '0 6px 11px #00000030' : '0 0px 0px #00000030'
    );
    this.renderer.listen(btn, 'click', () => {
      this.dir = i < this.currentCard ? 1 : -1;
      this.currentCard = i;
      this.moveCards(0.75);
    });
  });

  // Inicializar el movimiento de las tarjetas
  this.moveCards();
  }

  ngOnInit(): void {
  }

  applyPointerEffect(btn: HTMLElement, ease: string, shadow: string | (() => string)): void {
    this.renderer.listen(btn, 'pointerenter', () => {
      gsap.to(btn, { ease, 'box-shadow': typeof shadow === 'function' ? shadow() : shadow });
    });
    
    this.renderer.listen(btn, 'pointerleave', () => {
      gsap.to(btn, { ease, 'box-shadow': '0 6px 8px #00000030' });
    });
  }

  moveCards(dur: number = 0): void {
    gsap.timeline({
      defaults: {
        duration: dur,
        ease: 'power3',
        stagger: { each: -0.03 * this.dir }
      }
    })
      .to('.card', {
        x: -270 * this.currentCard,
        y: (i: number) => (i === this.currentCard ? 0 : 15),
        height: (i: number) => (i === this.currentCard ? 270 : 240),
        ease: 'elastic.out(0.4)'
      }, 0)
      .to('.card', {
        cursor: (i: number) => (i === this.currentCard ? 'default' : 'pointer'),
        'box-shadow': (i: number) => (i === this.currentCard ? '0 6px 11px #00000030' : '0 0px 0px #00000030'),
        border: (i: number) => (i === this.currentCard ? '2px solid #26a' : '0px solid #fff'),
        background: (i: number) => (i === this.currentCard ? 'radial-gradient(100% 100% at top, #fff 0%, #fff 99%)' : 'radial-gradient(100% 100% at top, #fff 20%, #eee 175%)'),
        ease: 'expo'
      }, 0)
      .to('.icon svg', {
        attr: {
          stroke: (i: number) => (i === this.currentCard ? 'transparent' : '#36a'),
          fill: (i: number) => (i === this.currentCard ? '#36a' : 'transparent')
        }
      }, 0)
      .to('.arrow-btn-prev, .arrow-btn-next', {
        autoAlpha: (i: number) => (i === 0 && this.currentCard === 0) || (i === 1 && this.currentCard === 4) ? 0 : 1
      }, 0)
      .to('.card h4', {
        y: (i: number) => (i === this.currentCard ? 0 : 8),
        opacity: (i: number) => (i === this.currentCard ? 1 : 0),
      }, 0);
  }
}
