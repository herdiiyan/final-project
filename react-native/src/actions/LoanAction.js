export const FIND_LOAN = "FIND_LOAN";
export const FIND_LOAN_SUCCES = "FIND_LOAN_SUCCES";
export const FIND_LOAN_ERROR = "FIND_LOAN_ERROR";

export function findLoan(accountNumber) {
  return {
    type: FIND_LOAN,
    accountNumber: accountNumber
  };
}