import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyWalletsComponent } from './my-wallets.component';

describe('MyWalletsComponent', () => {
  let component: MyWalletsComponent;
  let fixture: ComponentFixture<MyWalletsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyWalletsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyWalletsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
