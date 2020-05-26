import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { OrganizationUnitUpdateComponent } from 'app/entities/organization-unit/organization-unit-update.component';
import { OrganizationUnitService } from 'app/entities/organization-unit/organization-unit.service';
import { OrganizationUnit } from 'app/shared/model/organization-unit.model';

describe('Component Tests', () => {
  describe('OrganizationUnit Management Update Component', () => {
    let comp: OrganizationUnitUpdateComponent;
    let fixture: ComponentFixture<OrganizationUnitUpdateComponent>;
    let service: OrganizationUnitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [OrganizationUnitUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OrganizationUnitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OrganizationUnitUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OrganizationUnitService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OrganizationUnit(123);
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
        const entity = new OrganizationUnit();
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
