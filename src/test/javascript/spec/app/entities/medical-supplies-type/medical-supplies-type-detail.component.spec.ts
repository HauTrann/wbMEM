import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { MedicalSuppliesTypeDetailComponent } from 'app/entities/medical-supplies-type/medical-supplies-type-detail.component';
import { MedicalSuppliesType } from 'app/shared/model/medical-supplies-type.model';

describe('Component Tests', () => {
  describe('MedicalSuppliesType Management Detail Component', () => {
    let comp: MedicalSuppliesTypeDetailComponent;
    let fixture: ComponentFixture<MedicalSuppliesTypeDetailComponent>;
    const route = ({ data: of({ medicalSuppliesType: new MedicalSuppliesType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [MedicalSuppliesTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MedicalSuppliesTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalSuppliesTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medicalSuppliesType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medicalSuppliesType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
