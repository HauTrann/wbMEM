import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { MedicalDeviceMngTestModule } from '../../../test.module';
import { EquipmentTypeComponent } from 'app/entities/equipment-type/equipment-type.component';
import { EquipmentTypeService } from 'app/entities/equipment-type/equipment-type.service';
import { EquipmentType } from 'app/shared/model/equipment-type.model';

describe('Component Tests', () => {
  describe('EquipmentType Management Component', () => {
    let comp: EquipmentTypeComponent;
    let fixture: ComponentFixture<EquipmentTypeComponent>;
    let service: EquipmentTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalDeviceMngTestModule],
        declarations: [EquipmentTypeComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(EquipmentTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EquipmentTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EquipmentTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EquipmentType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.equipmentTypes && comp.equipmentTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EquipmentType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.equipmentTypes && comp.equipmentTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
