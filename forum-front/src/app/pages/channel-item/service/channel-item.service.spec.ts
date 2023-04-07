import { TestBed } from '@angular/core/testing';

import { ChannelItemService } from './channel-item.service';

describe('ChannelItemService', () => {
  let service: ChannelItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChannelItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
