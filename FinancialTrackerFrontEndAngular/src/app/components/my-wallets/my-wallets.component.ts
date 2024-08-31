import { AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';

@Component({
  selector: 'my-wallets',
  templateUrl: './my-wallets.component.html',
  styleUrls: ['./my-wallets.component.css']
})
export class MyWalletsComponent implements OnInit, AfterViewInit {
  @ViewChild('carousel', { static: false }) carousel!: ElementRef<HTMLDivElement>;
  @ViewChild('wrapper', { static: false }) wrapper!: ElementRef<HTMLDivElement>;
  @ViewChild('arrowLeft', { static: false }) arrowLeft!: ElementRef<HTMLDivElement>;
  @ViewChild('arrowRight', { static: false }) arrowRight!: ElementRef<HTMLDivElement>;

  private firstCardWidth!: number;
  private isDragging = false;
  private startX!: number;
  private startScrollLeft!: number;
  private timeoutId: any;

  constructor(private renderer: Renderer2) { }
  ngAfterViewInit(): void {
    const carousel = this.carousel.nativeElement;
    const wrapper = this.wrapper.nativeElement;
    const firstCard = carousel.querySelector(".card") as HTMLElement;
    this.firstCardWidth = firstCard.offsetWidth;
    
    // Event listeners
    this.renderer.listen(carousel, 'mousedown', this.dragStart.bind(this));
    this.renderer.listen(carousel, 'mousemove', this.dragging.bind(this));
    this.renderer.listen(document, 'mouseup', this.dragStop.bind(this));
    this.renderer.listen(wrapper, 'mouseenter', () => clearTimeout(this.timeoutId));
    this.renderer.listen(wrapper, 'mouseleave', this.autoPlay.bind(this));

    // Arrow buttons event listeners
    this.renderer.listen(this.arrowLeft.nativeElement, 'click', () => this.scrollCarousel(-this.firstCardWidth));
    this.renderer.listen(this.arrowRight.nativeElement, 'click', () => this.scrollCarousel(this.firstCardWidth));
    
  }

  ngOnInit(): void {
  }

  private dragStart(e: MouseEvent) {
    this.isDragging = true;
    this.renderer.addClass(this.carousel.nativeElement, 'dragging');
    this.startX = e.pageX;
    this.startScrollLeft = this.carousel.nativeElement.scrollLeft;
  }

  private dragging(e: MouseEvent) {
    if (!this.isDragging) return;

    const carousel = this.carousel.nativeElement;
    const newScrollLeft = this.startScrollLeft - (e.pageX - this.startX);

    if (newScrollLeft <= 0 || newScrollLeft >= carousel.scrollWidth - carousel.offsetWidth) {
      this.isDragging = false;
      return;
    }

    carousel.scrollLeft = newScrollLeft;
  }

  private dragStop() {
    this.isDragging = false;
    this.renderer.removeClass(this.carousel.nativeElement, 'dragging');
  }

  private autoPlay() {
    if (window.innerWidth < 800) return;

    const carousel = this.carousel.nativeElement;
    const totalCardWidth = carousel.scrollWidth;
    const maxScrollLeft = totalCardWidth - carousel.offsetWidth;

    if (carousel.scrollLeft >= maxScrollLeft) return;

    this.timeoutId = setTimeout(() => {
      carousel.scrollLeft += this.firstCardWidth;
      this.autoPlay();
    }, 2500);
  }

  private scrollCarousel(scrollAmount: number) {
    this.carousel.nativeElement.scrollLeft += scrollAmount;
  }

}
