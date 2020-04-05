import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { MedicalSuppliesTypeUpdateComponent } from 'app/entities/medical-supplies-type/medical-supplies-type-update.component';
import { MedicalSuppliesTypeService } from 'app/entities/medical-supplies-type/medical-supplies-type.service';
import { MedicalSuppliesType } from 'app/shared/model/medical-supplies-type.model';

describe('Component Tests', () => {
  describe('MedicalSuppliesType Management Update Component', () => {
    let comp: MedicalSuppliesTypeUpdateComponent;
    let fixture: ComponentFixture<MedicalSuppliesTypeUpdateComponent>;
    let service: MedicalSuppliesTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [MedicalSuppliesTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MedicalSuppliesTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MedicalSuppliesTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedicalSuppliesTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MedicalSuppliesType(123);
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
        const entity = new MedicalSuppliesType();
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
