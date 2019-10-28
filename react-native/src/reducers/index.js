import { combineReducers } from "redux";
import { findOneCustomers } from "./CustomersReducer";
import { findLoan } from "./LoanReducer";
import { getByCIF, getByAccountNumber, putBalance } from "./AccountReducer";
import { findBilling } from "./BillingReducer";
const allReducers = combineReducers({
  findOneCustomers,
  getByCIF,
  findLoan,
  findBilling,
  getByAccountNumber,
  putBalance
});
export default allReducers;
