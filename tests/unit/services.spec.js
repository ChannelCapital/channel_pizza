import { mount } from '@vue/test-utils'
import axios from 'axios';
import services from '@/services.js';

jest.mock('axios');

describe('api functionalities', () => {
  it('should adress data', () => {
    const places = [
      {
        "status": 200,
        "result": [
            {
                "code": "osgb4000000074310877",
                "name_1": "H M Naval Base",
                "name_1_lang": null,
                "name_2": null,
                "name_2_lang": null,
                "local_type": "Suburban Area",
                "outcode": "KY11",
                "county_unitary": "Fife",
                "county_unitary_type": "UnitaryAuthority",
                "district_borough": null,
                "district_borough_type": null,
                "region": "Scotland",
                "country": "Scotland",
                "longitude": -3.45157562057251,
                "latitude": 56.0338444061616,
                "eastings": 309647,
                "northings": 683278,
                "min_eastings": 309011,
                "min_northings": 681937,
                "max_eastings": 310482,
                "max_northings": 683299
            }
        ]
      }
    ];
    const response = {data: places};
    axios.get.mockResolvedValue(response);

    return axios.get(`http://api.postcodes.io/places?q=[H M Naval Base]`).then(data => expect(data.data).toEqual(places));
  })
}),

describe('api functionalities', () => {
  it('should address data by latitude and longitude', () => {
    const postcode = [
      {
        "status": 200,
        "result": [
          {
            "postcode": "M32 0JG",
            "quality": 1,
            "eastings": 379988,
            "northings": 395476,
            "country": "England",
            "nhs_ha": "North West",
            "longitude": -2.302836,
            "latitude": 53.455654,
            "european_electoral_region": "North West",
            "primary_care_trust": "Trafford",
            "region": "North West",
            "lsoa": "Trafford 003C",
            "msoa": "Trafford 003",
            "incode": "0JG",
            "outcode": "M32",
            "parliamentary_constituency": "Stretford and Urmston",
            "admin_district": "Trafford",
            "parish": "Trafford, unparished area",
            "admin_county": null,
            "admin_ward": "Gorse Hill",
            "ced": null,
            "ccg": "NHS Trafford",
            "nuts": "Greater Manchester South West",
            "codes": {
                "admin_district": "E08000009",
                "admin_county": "E99999999",
                "admin_ward": "E05000829",
                "parish": "E43000163",
                "parliamentary_constituency": "E14000979",
                "ccg": "E38000187",
                "ccg_id": "02A",
                "ced": "E99999999",
                "nuts": "TLD34",
                "lsoa": "E01006187",
                "msoa": "E02001261",
                "lau2": "E08000009"
            },
            "distance": 0
        },
        {
            "postcode": "M32 0JQ",
            "quality": 1,
            "eastings": 380010,
            "northings": 395459,
            "country": "England",
            "nhs_ha": "North West",
            "longitude": -2.302504,
            "latitude": 53.455502,
            "european_electoral_region": "North West",
            "primary_care_trust": "Trafford",
            "region": "North West",
            "lsoa": "Trafford 003C",
            "msoa": "Trafford 003",
            "incode": "0JQ",
            "outcode": "M32",
            "parliamentary_constituency": "Stretford and Urmston",
            "admin_district": "Trafford",
            "parish": "Trafford, unparished area",
            "admin_county": null,
            "admin_ward": "Gorse Hill",
            "ced": null,
            "ccg": "NHS Trafford",
            "nuts": "Greater Manchester South West",
            "codes": {
                "admin_district": "E08000009",
                "admin_county": "E99999999",
                "admin_ward": "E05000829",
                "parish": "E43000163",
                "parliamentary_constituency": "E14000979",
                "ccg": "E38000187",
                "ccg_id": "02A",
                "ced": "E99999999",
                "nuts": "TLD34",
                "lsoa": "E01006187",
                "msoa": "E02001261",
                "lau2": "E08000009"
            },
            "distance": 27.79509959
        },
        {
            "postcode": "M32 0JL",
            "quality": 1,
            "eastings": 379958,
            "northings": 395501,
            "country": "England",
            "nhs_ha": "North West",
            "longitude": -2.30329,
            "latitude": 53.455877,
            "european_electoral_region": "North West",
            "primary_care_trust": "Trafford",
            "region": "North West",
            "lsoa": "Trafford 003C",
            "msoa": "Trafford 003",
            "incode": "0JL",
            "outcode": "M32",
            "parliamentary_constituency": "Stretford and Urmston",
            "admin_district": "Trafford",
            "parish": "Trafford, unparished area",
            "admin_county": null,
            "admin_ward": "Gorse Hill",
            "ced": null,
            "ccg": "NHS Trafford",
            "nuts": "Greater Manchester South West",
            "codes": {
                "admin_district": "E08000009",
                "admin_county": "E99999999",
                "admin_ward": "E05000829",
                "parish": "E43000163",
                "parliamentary_constituency": "E14000979",
                "ccg": "E38000187",
                "ccg_id": "02A",
                "ced": "E99999999",
                "nuts": "TLD34",
                "lsoa": "E01006187",
                "msoa": "E02001261",
                "lau2": "E08000009"
            },
            "distance": 39.05760972
        },
        {
            "postcode": "M32 0JW",
            "quality": 1,
            "eastings": 380030,
            "northings": 395435,
            "country": "England",
            "nhs_ha": "North West",
            "longitude": -2.302201,
            "latitude": 53.455287,
            "european_electoral_region": "North West",
            "primary_care_trust": "Trafford",
            "region": "North West",
            "lsoa": "Trafford 003C",
            "msoa": "Trafford 003",
            "incode": "0JW",
            "outcode": "M32",
            "parliamentary_constituency": "Stretford and Urmston",
            "admin_district": "Trafford",
            "parish": "Trafford, unparished area",
            "admin_county": null,
            "admin_ward": "Gorse Hill",
            "ced": null,
            "ccg": "NHS Trafford",
            "nuts": "Greater Manchester South West",
            "codes": {
                "admin_district": "E08000009",
                "admin_county": "E99999999",
                "admin_ward": "E05000829",
                "parish": "E43000163",
                "parliamentary_constituency": "E14000979",
                "ccg": "E38000187",
                "ccg_id": "02A",
                "ced": "E99999999",
                "nuts": "TLD34",
                "lsoa": "E01006187",
                "msoa": "E02001261",
                "lau2": "E08000009"
            },
            "distance": 58.71680594
        },
        {
            "postcode": "M32 0JY",
            "quality": 1,
            "eastings": 379906,
            "northings": 395504,
            "country": "England",
            "nhs_ha": "North West",
            "longitude": -2.304073,
            "latitude": 53.455902,
            "european_electoral_region": "North West",
            "primary_care_trust": "Trafford",
            "region": "North West",
            "lsoa": "Trafford 003C",
            "msoa": "Trafford 003",
            "incode": "0JY",
            "outcode": "M32",
            "parliamentary_constituency": "Stretford and Urmston",
            "admin_district": "Trafford",
            "parish": "Trafford, unparished area",
            "admin_county": null,
            "admin_ward": "Gorse Hill",
            "ced": null,
            "ccg": "NHS Trafford",
            "nuts": "Greater Manchester South West",
            "codes": {
                "admin_district": "E08000009",
                "admin_county": "E99999999",
                "admin_ward": "E05000829",
                "parish": "E43000163",
                "parliamentary_constituency": "E14000979",
                "ccg": "E38000187",
                "ccg_id": "02A",
                "ced": "E99999999",
                "nuts": "TLD34",
                "lsoa": "E01006187",
                "msoa": "E02001261",
                "lau2": "E08000009"
            },
            "distance": 86.68323913
        },
        {
            "postcode": "M32 0JN",
            "quality": 1,
            "eastings": 380057,
            "northings": 395409,
            "country": "England",
            "nhs_ha": "North West",
            "longitude": -2.301793,
            "latitude": 53.455054,
            "european_electoral_region": "North West",
            "primary_care_trust": "Trafford",
            "region": "North West",
            "lsoa": "Trafford 003C",
            "msoa": "Trafford 003",
            "incode": "0JN",
            "outcode": "M32",
            "parliamentary_constituency": "Stretford and Urmston",
            "admin_district": "Trafford",
            "parish": "Trafford, unparished area",
            "admin_county": null,
            "admin_ward": "Gorse Hill",
            "ced": null,
            "ccg": "NHS Trafford",
            "nuts": "Greater Manchester South West",
            "codes": {
                "admin_district": "E08000009",
                "admin_county": "E99999999",
                "admin_ward": "E05000829",
                "parish": "E43000163",
                "parliamentary_constituency": "E14000979",
                "ccg": "E38000187",
                "ccg_id": "02A",
                "ced": "E99999999",
                "nuts": "TLD34",
                "lsoa": "E01006187",
                "msoa": "E02001261",
                "lau2": "E08000009"
            },
            "distance": 96.22676454
        }
    ]
      }
    ];
    const response = {data: postcode};
    axios.get.mockResolvedValue(response);

    return axios.get(`https://api.postcodes.io/postcodes?lon=-2.302836&lat=53.455654`).then(data => expect(data.data).toEqual(postcode));
  })
}),

describe('db functionalities', () => {
  it('should connect db and return null before enter a product', () => {
    const db = [
      {
        "status": 200,
        "result": []
      }
    ];
    const response = {data:db};
    axios.get.mockResolvedValue(response);

    return services.getProducts().then(data => expect(data.data).toEqual(db));
  })
}),
describe('db functionalities', () => {
  it('should connect db and return null before enter a product to basket', () => {
    const db = [
      {
        "status": 200,
        "result": []
      }
    ];
    const response = {data:db};
    axios.get.mockResolvedValue(response);

    return services.getBasket().then(data => expect(data.data).toEqual(db));
  })
}),

describe('db functionalities', () => {
  it('should connect db and return null before enter a user', () => {
    const db = [
      {
        "status": 200,
        "result": []
      }
    ];
    const response = {data:db};
    axios.get.mockResolvedValue(response);

    return services.getUsers().then(data => expect(data.data).toEqual(db));
  })
})
