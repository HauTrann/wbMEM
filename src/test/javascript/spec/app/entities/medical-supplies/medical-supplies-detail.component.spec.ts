import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { MedicalSuppliesDetailComponent } from 'app/entities/medical-supplies/medical-supplies-detail.component';
import { MedicalSupplies } from 'app/shared/model/medical-supplies.model';

describe('Component Tests', () => {
  describe('MedicalSupplies Management Detail Component', () => {
    let comp: MedicalSuppliesDetailComponent;
    let fixture: ComponentFixture<MedicalSuppliesDetailComponent>;
    const route = ({ data: of({ medicalSupplies: new MedicalSupplies(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [MedicalSuppliesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MedicalSuppliesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalSuppliesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medicalSupplies on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medicalSupplies).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
