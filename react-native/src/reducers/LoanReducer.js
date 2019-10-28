import {
    FIND_LOAN,
    FIND_LOAN_ERROR,
    FIND_LOAN_SUCCES
  } from "../actions/LoanAction";
  
  const initFindOneState = {
    data: [],
    loading: false,
    error: null
  };
  export function findLoan(state = initFindOneState, action) {
    console.log(action);
    switch (action.type) {
      case FIND_LOAN:
        return {
          ...initFindOneState,
          loading: true,
          error: null
        };
      case FIND_LOAN_SUCCES:
        return {
          ...state,
          data: action.data,
          loading: false,
          error: null
        };
      case FIND_LOAN_ERROR:
        return {
          ...state,
          error: action.error,
          loading: false
        };
      default:
        return state;
    }
  }
  