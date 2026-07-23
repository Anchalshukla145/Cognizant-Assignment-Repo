import React from 'react';

export default function App() {
  const office = {
    name: 'Cognizant Premium Hub',
    rent: 75000,
    address: 'DLF IT Park, Tower A, Kolkata'
  };

  const officesList = [
    { id: 1, name: 'Eco-Cabin Startup Space', rent: 45000, address: 'Action Area II, New Town' },
    { id: 2, name: 'Technopolis Corporate Suite', rent: 82000, address: 'Sector V, Salt Lake' },
    { id: 3, name: 'Infinity Tech Shared Space', rent: 58000, address: 'Sector V, Salt Lake' }
  ];

  return (
    <div>
      <h2>Office Space Rental App</h2>
      <h3>Featured Office</h3>
      <p>Name: {office.name}</p>
      <p>Address: {office.address}</p>
      <p style={{ color: office.rent < 60000 ? 'red' : 'green' }}>
        Rent: INR {office.rent}
      </p>

      <h3>All Offices</h3>
      {officesList.map(item => (
        <div key={item.id} style={{ border: '1px solid #ccc', margin: '10px 0', padding: '10px' }}>
          <p>Name: {item.name}</p>
          <p>Address: {item.address}</p>
          <p style={{ color: item.rent < 60000 ? 'red' : 'green' }}>
            Rent: INR {item.rent}
          </p>
        </div>
      ))}
    </div>
  );
}
