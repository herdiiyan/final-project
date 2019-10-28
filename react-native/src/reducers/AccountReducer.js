import {
  FIND_ACCOUNT,
  FIND_ACCOUNT_ERROR,
  FIND_ACCOUNT_SUCCES,
  FIND_ACCOUNT_NUMBER,
  FIND_ACCOUNT_NUMBER_SUCCES,
  FIND_ACCOUNT_NUMBER_ERROR,
  PUT_ACCOUNT_BALANCE,
  PUT_ACCOUNT_BALANCE_SUCCES,
  PUT_ACCOUNT_BALANCE_ERROR
} from "../actions/CustomerAccount";

const initFindOneState = {
  data: [],
  loading: false,
  error: null
};

const initState = {
  data: null,
  loading: false,
  error:null
};

export function getByCIF(state = initFindOneState, action) {
  console.log(action);
  switch (action.type) {
    case FIND_ACCOUNT:
      return {
        ...initFindOneState,
        loading: true,
        error: null
      };
    case FIND_ACCOUNT_SUCCES:
      return {
        ...state,
        data: action.data,
        loading: false,
        error: null
      };
    case FIND_ACCOUNT_ERROR:
      return {
        ...state,
        error: action.error,
        loading: false
      };
    default:
      return state;
  }
}

export function getByAccountNumber(state = initFindOneState, action) {
  console.log(action);
  switch (action.type) {
    case FIND_ACCOUNT_NUMBER:
      return {
        ...initFindOneState,
        loading: true,
        error: null
      };
    case FIND_ACCOUNT_NUMBER_SUCCES:
      return {
        ...state,
        data: action.data,
        loading: false,
        error: null
      };
    case FIND_ACCOUNT_NUMBER_ERROR:
      return {
        ...state,
        error: action.error,
        loading: false
      };
    default:
      return state;
  }
}

export function putBalance(state = initState, action) {
  switch (action.type) {
    case PUT_ACCOUNT_BALANCE:
      return {
        ...initState,
        loading: true,
        error: null
      };
    case PUT_ACCOUNT_BALANCE_SUCCES:
      return {
        ...state,
        data: action.data,
        loading: false,
        error: null
      };
    case PUT_ACCOUNT_BALANCE_ERROR:
      return {
        ...state,
        error: action.error,
        loading: false
      };
    default:
      return state;
  }
}
