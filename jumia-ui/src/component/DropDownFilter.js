import { Component } from "react";

export default class DropDownFilter extends Component {
  constructor() {
    super();
    this.state = {};
  }

  componentDidMount() {
    this.setState({
      countries: this.props.countries,
      states: this.props.states,
    });
  }

  render() {
    const stateOptions = this.props.states.map((state) => {
      return <option>{state}</option>;
    });
    const countryOptions = this.props.countries.map((country) => {
      return <option>{country}</option>;
    });
    return (
      <div
        class="filtersWrapper"
        style={{ width: `100%`, display: `inline-flex` }}
      >
        <div
          class="filter"
          style={{ width: `16%`, display: `inline-flex`, paddingRight: `9.5%` }}
        >
          <div class="filter__label" style={{ width: `45%` }}>
            <label>Filter By State:</label>
          </div>
          <div style={{ width: `50%` }}>
            <select
              style={{ width: `100%` }}
              defaultValue="vesselName"
              onChange={(e) => this.props.onChangeStateFilter(e.target.value)}
            >
              {stateOptions}
            </select>
          </div>
        </div>
        <div class="filter" style={{ width: `20%`, display: `inline-flex` }}>
          <div class="filter__label" style={{ width: `45%` }}>
            <label>Filter By Country:</label>
          </div>
          <div class="filter__select" style={{ width: `40%` }}>
            <select
              style={{ width: `100%` }}
              defaultValue="vesselName"
              onChange={(e) => this.props.onChangeCountryFilter(e.target.value)}
            >
              {countryOptions}
            </select>
          </div>
        </div>
      </div>
    );
  }
}
