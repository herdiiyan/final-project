import {
  FIND_CUSTOMER,
  FIND_CUSTOMER_ERROR,
  FIND_CUSTOMER_SUCCES,
} from "../actions/CustomersActions";

const initFindOneState = {
  data: {},
  loading: false
};
export function findOneCustomers(state = initFindOneState, action) {
  console.log(action);
  switch (action.type) {
    case FIND_CUSTOMER:
      return {
        ...initFindOneState,
        loading: true,
        error: null
      };
    case FIND_CUSTOMER_SUCCES:
      return {
        ...state,
        data: action.data,
        loading: false,
        error: null
      };
    case FIND_CUSTOMER_ERROR:
      return {
        ...state,
        error: action.error,
        loading: false
      };
    default:
      return state;
  }
}
