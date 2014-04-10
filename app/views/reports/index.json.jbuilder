json.array!(@reports) do |report|
  json.extract! report, :title, :lat, :lng, :description
  json.url report_url(report, format: :json)
end