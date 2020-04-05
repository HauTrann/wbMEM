import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { MedicalSuppliesUpdateComponent } from 'app/entities/medical-supplies/medical-supplies-update.component';
import { MedicalSuppliesService } from 'app/entities/medical-supplies/medical-supplies.service';
import { MedicalSupplies } from 'app/shared/model/medical-supplies.model';

describe('Component Tests', () => {
  describe('MedicalSupplies Management Update Component', () => {
    let comp: MedicalSuppliesUpdateComponent;
    let fixture: ComponentFixture<MedicalSuppliesUpdateComponent>;
    let service: MedicalSuppliesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [MedicalSuppliesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MedicalSuppliesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalSuppliesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalSuppliesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalSupplies(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalSupplies();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
