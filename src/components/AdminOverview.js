import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../css/AdminOverview.css';

const AdminOverview = () => {
    const [rentedCars, setRentedCars] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/rentals')
            .then((response) => {
                const carsWithRevenue = response.data.map(car => {
                    const rentalDays = (new Date(car.returnDate) - new Date(car.pickupDate)) / (1000 * 60 * 60 * 24);
                    const revenue = rentalDays * car.pricePerDay; 
                    return { ...car, revenue };
                });
                setRentedCars(carsWithRevenue);
            })
            .catch((error) => {
                console.error('Error fetching rented cars:', error);
            });
    }, []);

    const totalRevenue = rentedCars.reduce((sum, car) => sum + car.revenue, 0);

    return (
        <div className="admin-overview">
            <div className="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Driver</th>
                            <th>Car</th>
                            <th>Rental Dates</th>
                            <th>Revenue</th>
                        </tr>
                    </thead>
                    <tbody>
                        {rentedCars.map((car) => (
                            <tr key={car.id}>
                                <td>{car.driverName}</td>
                                <td>{car.carSelection}</td>
                                <td>{new Date(car.pickupDate).toLocaleDateString()} to {new Date(car.returnDate).toLocaleDateString()}</td>
                                <td>{car.revenue} kr</td>
                            </tr>
                        ))}
                        <tr>
                            <td colSpan="3">Total Revenue</td>
                            <td>{totalRevenue} kr</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AdminOverview;