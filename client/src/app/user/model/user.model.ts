import {EventCategory} from '../../event-category/model/event-category.model';

export class User {

  username: string;
  name: string;
  surname: string;
  email: string;
  city: string;
  street: string;
  houseNo: number;
  flatNo: number;
  averageVote: number;
  eventCategories: EventCategory[];
}
