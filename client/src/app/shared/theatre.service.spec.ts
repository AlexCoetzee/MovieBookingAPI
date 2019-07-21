import { TestBed } from '@angular/core/testing';

import { TheatreService } from './theatre.service';

describe('TheatreService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TheatreService = TestBed.get(TheatreService);
    expect(service).toBeTruthy();
  });
});
