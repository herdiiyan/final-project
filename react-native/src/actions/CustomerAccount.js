export const FIND_ACCOUNT = "FIND_ACCOUNT";
export const FIND_ACCOUNT_SUCCES = "FIND_ACCOUNT_SUCCES";
export const FIND_ACCOUNT_ERROR = "FIND_ACCOUNT_ERROR";

export const FIND_ACCOUNT_NUMBER = "FIND_ACCOUNT_NUMBER";
export const FIND_ACCOUNT_NUMBER_SUCCES = "FIND_ACCOUNT_NUMBER_SUCCES";
export const FIND_ACCOUNT_NUMBER_ERROR = "FIND_ACCOUNT_NUMBER_ERROR";

export const PUT_ACCOUNT_BALANCE = "PUT_ACCOUNT_BALANCE";
export const PUT_ACCOUNT_BALANCE_SUCCES = "PUT_ACCOUNT_BALANCE_SUCCES";
export const PUT_ACCOUNT_BALANCE_ERROR = "PUT_ACCOUNT_BALANCE_ERROR";

export function getAccountByCIF(cif) {
  return {
    type: FIND_ACCOUNT,
    cif: cif
  };
}

export function getAccountByAccountNumber(accountNumber) {
  return {
    type: FIND_ACCOUNT_NUMBER,
    accountNumber: accountNumber
  };
}

export function putBalance(accountNumber, data) {
  return {
    type: PUT_ACCOUNT_BALANCE,
    accountNumber: accountNumber,
    data: data
  };
}