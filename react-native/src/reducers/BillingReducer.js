import {
    FIND_BILLING,
    FIND_BILLING_ERROR,
    FIND_BILLING_SUCCES
  } from "../actions/BiliingAction";
  
  const initFindOneState = {
    data: [],
    loading: false,
    error: null
  };
  export function findBilling(state = initFindOneState, action) {
    console.log(action);
    switch (action.type) {
      case FIND_BILLING:
        return {
          ...initFindOneState,
          loading: true,
          error: null
        };
      case FIND_BILLING_SUCCES:
        return {
          ...state,
          data: action.data,
          loading: false,
          error: null
        };
      case FIND_BILLING_ERROR:
        return {
          ...state,
          error: action.error,
          loading: false
        };
      default:
        return state;
    }
  }
  