import React from 'react';

function CalculateScore(props) {
  const average = (props.Total / props.goal) * 100;
  return (
    <div>
      <p>Name: {props.Name}</p>
      <p>School: {props.School}</p>
      <p>Total: {props.Total}</p>
      <p>Goal: {props.goal}</p>
      <p>Average: {average}%</p>
    </div>
  );
}

export default function App() {
  return (
    <div>
      <h2>Score Calculator</h2>
      <CalculateScore Name="Anchal Shukla" School="Cognizant Academy" Total={450} goal={500} />
    </div>
  );
}
