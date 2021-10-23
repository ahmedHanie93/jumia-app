import { Component } from 'react';
import DataTable from 'react-data-table-component';
import PhoneNumbersApi from '../rest/PhoneNumbersApi';
import DropDownFilter from './DropDownFilter';

const ALL = "ALL";

export default class Table extends Component{
    
    constructor(){
        super();
        this.columns = [
            {
                name: 'Phone Number',
                selector: row => row.number,
            },
            {
                name: 'Country',
                selector: row => row.country.name,
            },
            {
                name: 'Code',
                selector: row => row.country.code,
            },
            {
                name: 'State',
                selector: row => row.state,
            },
        ];
        this.state = {
            phoneNumbers:[],
            states:[],
            countries:[],
            selectedFilterState: undefined,
            selectedFilterCountry: undefined,
        };
        this.onChangeStateFilter = this.onChangeStateFilter.bind(this);
        this.onChangeCountryFilter = this.onChangeCountryFilter.bind(this);
        this.getTableData = this.getTableData.bind(this);
        this.getFilterCondition = this.getFilterCondition.bind(this);
    }

    componentDidMount(){
        PhoneNumbersApi.getPhoneNumbers().then(phoneNumbers =>{
            const countries = new Set();
            const states = new Set();

            if(phoneNumbers){
                countries.add(ALL);
                states.add(ALL);

                phoneNumbers.data.forEach(num => {
                    countries.add(num.country.name);
                    states.add(num.state);
                });
            }
            
            this.setState({
                phoneNumbers: phoneNumbers.data,
                states: [...states],
                countries: [...countries],
                tableData: phoneNumbers.data,
            })
        });
    }

    onChangeStateFilter(filteredValue){
        this.setState({selectedFilterState:filteredValue});
    }

    onChangeCountryFilter(filteredValue){
        this.setState({selectedFilterCountry:filteredValue});
    }

    getTableData(){
        let phoneNumbers = [...this.state.phoneNumbers];
        phoneNumbers = phoneNumbers.filter(number => this.getFilterCondition(number));
        return phoneNumbers;
    }

    getFilterCondition(number){
        let condition = true;

        if(this.state.selectedFilterState && this.state.selectedFilterState !== ALL){
            condition = condition && number.state === this.state.selectedFilterState;
        } 
        if(this.state.selectedFilterCountry && this.state.selectedFilterCountry !== ALL){
            condition = condition && number.country.name === this.state.selectedFilterCountry;
        }
        return condition;
    }

    render() {
        const filter =  
                <DropDownFilter 
                    onChangeStateFilter={this.onChangeStateFilter}
                    onChangeCountryFilter={this.onChangeCountryFilter}
                    countries={this.state.countries || []}
                    states={this.state.states || []}/>;
        return (
            <div className="App">
                <DataTable
                    title="Phone numbers"
                    columns={this.columns}
                    data={this.getTableData() || []}
                    subHeader
                    subHeaderComponent= {filter}
                    pagination
                />
            </div>
        );
    };
}